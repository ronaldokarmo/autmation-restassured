package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import org.junit.Assert;

import java.util.HashMap;

import static support.context.Context.rest;

public class GenericSteps {

    @Entao("deve apresentar a mensagem {string}")
    public void deveApresentarAMensagem(String message) {
        HashMap<String, Object> messageJson = new HashMap<String, Object>();
        messageJson.put("salvo com sucesso", 201);
        messageJson.put("sucesso", 200);
        messageJson.put("sucesso sem informação", 204);
        messageJson.put("não encontrado", 404);
        messageJson.put("não autorizado", 401);

        Assert.assertEquals(messageJson.get(message), rest().getResponse().getStatusCode());
    }

    @E("o campo {string} deve conter {string}")
    public void oCampoDeveConter(String field, String value) {
        Assert.assertEquals(value, rest().key(field));
    }
}
