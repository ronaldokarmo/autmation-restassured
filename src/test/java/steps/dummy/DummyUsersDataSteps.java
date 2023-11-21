package steps.dummy;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import services.BaseService;
import services.DummyServices;

import static support.context.Context.rest;

public class DummyUsersDataSteps {
    private String id;
    String firstName, lastName;

    @Dado("a necessidade de incluir novo usuário na Rede Social")
    public void aNecessidadeDeIncluirNovoUsuárioNaRedeSocial() {
        DummyServices.prepare();
    }

    @Quando("executar a requisição da API Post Create User")
    public void executarARequisiçãoDaAPIPostCreateUser() {
        DummyServices.post(DummyServices.setRequestBody(), "/create");
//        DummyServices.post(DummyServices.setRequestBodyMap(), "/create");
        id = rest().key("id").toString();
        firstName = rest().key("firstName").toString();
        lastName = rest().key("lastName").toString();
    }

    @Então("a requisição deve retornar os dados do novo usuário cadastrado na base da Rede Social")
    public void aRequisiçãoDeveRetornarOsDadosDoNovoUsuárioCadastradoNaBaseDaRedeSocial() {
        if (rest().getResponse().getStatusCode() == HttpStatus.SC_OK) {
            DummyServices.assertValidSchema("schemas/dummyUserDataPost-schema.json");
            Assert.assertNotNull(rest().key("id"));
            Assert.assertNotNull(rest().key("firstName"));
            Assert.assertNotNull(rest().key("lastName"));
            Assert.assertNotNull(rest().key("email"));
            Assert.assertNotNull(rest().key("registerDate"));
            Assert.assertNotNull(rest().key("updatedDate"));

            Assert.assertEquals(id, rest().key("id"));
            Assert.assertEquals(firstName, rest().key("firstName"));
            Assert.assertEquals(lastName, rest().key("lastName"));
        }
    }

    @Dado("a necessidade de consultar todos os dados dos usuários da Rede Social")
    public void aNecessidadeDeConsultarTodosOsDadosDosUsuáriosDaRedeSocial() {
        DummyServices.prepare();
        BaseService.post(DummyServices.setRequestBody(), "/create");
        id = rest().key("id").toString();
        firstName = rest().key("firstName").toString();
        lastName = rest().key("lastName").toString();
        Assert.assertEquals(HttpStatus.SC_OK, rest().getResponse().statusCode());
    }

    @Quando("executar a requisição da API Get List Users")
    public void executarARequisiçãoDaAPIGetListUsers() {
        BaseService.get(id);
    }

    @Então("a requisição deve retornar os dados de todos os usuários cadastrados na base da Rede Social")
    public void aRequisiçãoDeveRetornarOsDadosDeTodosOsUsuáriosCadastradosNaBaseDaRedeSocial() {
        Assert.assertEquals(HttpStatus.SC_OK, rest().getResponse().statusCode());
        BaseService.assertValidSchema("schemas/dummyUserDataGet-schema.json");
        Assert.assertEquals(id, rest().key("id"));
        Assert.assertEquals(firstName, rest().key("firstName"));
        Assert.assertEquals(lastName, rest().key("lastName"));
    }
}
