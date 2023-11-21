package services;

import business.PetStore;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.util.*;

import static support.context.Context.rest;

/**
 * Esta classe contém todos os recursos necessários para Controle da API PetStore. Ela é usada para:
 *  - Setar a Massas de Dados utilizada nos testes.
 *  - Criar Metódos ou Assert espeficicos de uma regra de negócio
 *
 */
public class PetStoreServices extends BaseService {
    /**
     * Este metódo prepara as pre-condições dos testes as serem executados, como por exemplo
     * - Objeto Rest assured para as requisições;
     * - Parâmetros do Endpoint: url e path;
     * - Parâmetros do header
     *
     */
    public static void prepare() {
        rest().setBaseURI("https://petstore.swagger.io");
        BASEURI = PET;
        rest().newRequest();
        rest().header("Content-Type", "application/json");
        rest().header("Accept", "application/json");
    }

    /**
     * Este metódo seta a massa de dados num documento no formato Json. Para que seja possivel o envio da requisição PetStore Post Create Pet.
     * * Aqui o Recurso Map com ometódo put nós ajuda a transaformar uma String em um documento Json.
     *
     * @return retorna um documento no formato Json para ser utilizado no RequestBody da requisição PetStore Post Create Pet.
     */
    public static Map<String, Object> setDataRequestBodyMap() {
        final Map<String, Object> requestBody = new HashMap<>();

        Map<String, Object> category = new HashMap<>();
        category.put("id", 1);
        category.put("name", "Dogs");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string");

        Map<String, Object> tag = new HashMap<>();
        tag.put("id", 2021);
        tag.put("name", "breed");

        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(tag);

        requestBody.put("id", 1984020712);
        requestBody.put("category", category);
        requestBody.put("name", "Nainai");
        requestBody.put("photoUrls", photoUrls);
        requestBody.put("tags", tags);
        requestBody.put("status", "available");

        return requestBody;
    }

    /**
     * Este metódo verifica o retorno do status code e responseBody conforme uma regra de negócio.
     *
     * @param id valida se o valor do Id do Pet está de acordo com o retornado no responseBody
     * @param name valida o valor do Name do Pet está de acordo com o retornado no responseBody
     */
    public static void assertResponseBodyAndStatusCodes(int id, String name) {
        if (rest().getResponse().getStatusCode() == HttpStatus.SC_OK) {
            BaseService.assertValidSchema("schemas/petStore-schema.json");
            Assert.assertEquals(id, rest().key("id"));
            Assert.assertEquals(name, rest().key("name"));
        }
        else if(rest().getResponse().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            BaseService.assertValidSchema("schemas/petStore-error-schema.json");
        }
        else if(rest().getResponse().getStatusCode() == HttpStatus.SC_METHOD_NOT_ALLOWED) {
            rest().getResponse();
        }
    }
}
