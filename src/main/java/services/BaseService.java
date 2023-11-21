package services;

import constants.EndPoints;
import org.hamcrest.MatcherAssert;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static support.context.Context.rest;

public class BaseService implements EndPoints {
    protected static String BASEURI = "";

    /**
     * Este metódo permite o envio de uma requisição GET sem a necessidade da passagem de parâmetros.
     * @param id Parâmetro de Id do Pet.
     *
     */
    public static void get(int id) {
        rest().setResponse(rest().getRequest().get(BASEURI + '/' + id));
    }

    /**
     * Este metódo permite o envio de uma requisição GET com a passagem do parâmetro.
     * @param id Parâmetro de Id do Pet.
     *
     */
    public static void get(String id) {
        rest().setResponse(rest().getRequest().get(BASEURI + '/' + id));
    }

    /**
     * Este metódo permite o envio de uma requisição POST com a passagem do parâmetro.
     * @param body Parâmetro com os dados do requestBody no formato Json conforme definido na Classe PetStore.
     *
     */
    public static void post(String body) {
        rest().setResponse(rest().getRequest().body(body).post(BASEURI));
    }

    /**
     * Este metódo permite o envio de uma requisição POST com a passagem do parâmetro.
     * @param body Parâmetro com os dados do requestBody no formato Json conforme definido na Classe PetStore.
     * @param url Parâmetro com a concatenação que será atribuída a url da requisição.
     *
     */
    public static void post(String body, String url) {
        rest().setResponse(rest().getRequest().body(body).post(BASEURI.concat(url)));
    }

    /**
     * Este metódo permite o envio de uma requisição POST com a passagem do parâmetro.
     * @param body Parâmetro com o valor do Recurso Map que ajuda a transaformar uma String em um documento Json que será atribuído ao requestBody.
     *
     */
    public static void post(Map<String, Object> body) {
        rest().setResponse(rest().getRequest().body(body).post(BASEURI));
    }

    /**
     * Este metódo permite o envio de uma requisição POST com a passagem do parâmetro.
     * @param body Parâmetro com o valor do Recurso Map que ajuda a transaformar uma String em um documento Json que será atribuído ao requestBody.
     * @param url Parâmetro com a concatenação que será atribuída a url da requisição.
     *
     */
    public static void post(Map<String, Object> body, String url) {
        rest().setResponse(rest().getRequest().body(body).post(BASEURI.concat(url)));
    }

    /**
     * Este metódo permite o envio de uma requisição POST com a passagem do parâmetro.
     * @param body Parâmetro com o valor do Recurso Map que ajuda a transaformar uma String em um documento Json que será atribuído ao requestBody.
     * @param id Parâmetro com o valor do Id que utilizado na validação da requisição Get por Id.
     *
     */
    public static void put(Map<String, Object> body, String id) {
        rest().setResponse(rest().getRequest().body(body).put(BASEURI + '/' + id));
    }

    /**
     * Este metódo permite o envio de uma requisição POST com a passagem do parâmetro.
     * @param id Parâmetro com o valor do Id que utilizado na validação da requisiçaõ Get por Id.
     *
     */
    public static void delete(String id) {
        rest().setResponse(rest().getRequest().delete(BASEURI + '/' + id));
    }

    /**
     * Este metódo permite validar o Schema Json retornado no responseBody.
     * @param fileSchema Parâmetro com o caminho do arquivo json utilizado na validação do schema de contrato.
     *
     */
    public static void assertValidSchema(String fileSchema) {
        MatcherAssert.assertThat(rest().getResponse().body().prettyPrint(), matchesJsonSchemaInClasspath(fileSchema));
    }
}
