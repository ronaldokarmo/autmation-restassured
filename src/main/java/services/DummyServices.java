package services;

import business.dummy.UserData;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import static support.context.Context.rest;

/**
 * Esta classe contém todos os recursos necessários para Controle de usuários da Rede Social Dummy. Ela é usada para:
 *  - Setar a Massas de Dados utilizada nos testes.
 *  - Criar Metódos ou Assert espeficicos de uma regra de negócio
 *
 */
public class DummyServices extends BaseService {
    private static Faker faker;

    /**
     * Este metódo prepara as pre-condições dos testes as serem executados, como por exemplo
     * - Objeto Rest assured para as requisições;
     * - Parâmetros do Endpoint: url e path;
     * - Parâmetros do header
     *
     */
    public static void prepare() {
        rest().setBaseURI("https://dummyapi.io/");
        BASEURI = DUMMYUSERDATA;
        faker = new Faker();
        rest().newRequest();
        rest().header("Content-Type", "application/json");
        rest().header("Accept", "application/json");
        rest().header("app-id", "654506ebefb5caf671d80949");
    }

    /**
     * Este metódo seta um documento no formato Json, a partir da Classe User. Para que seja possivel o envio da requisição Dummy Post Create User.
     * Aqui o Recurso Gson ajuda na aplicação da Tecnica de Serialization, ou seja transaforma uma classe em um documento Json.
     *
     * @return retorna um documento no formato Json para ser utilizado no RequestBody da requisição Dummy Post Create User.
     */
    public static String setRequestBody() {
        UserData userData = new UserData();
        userData.setFirstName(faker.name().firstName());
        userData.setLastName(faker.name().lastName());
        userData.setEmail(faker.internet().emailAddress());
        Gson gson = new Gson();
        return gson.toJson(userData);
    }
}
