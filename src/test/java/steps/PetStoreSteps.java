package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import services.BaseService;
import services.PetStoreServices;

import static support.context.Context.rest;

public class PetStoreSteps {
    private int id;
    private String name;

    @Dado("a necessidade de cadastrar um novo Pet na PetStore")
    public void aNecessidadeDeCadastrarUmNovoPetNaPetStore() {
        PetStoreServices.prepare();
    }

    @Quando("executar a requisição Post PetStore")
    public void executarARequisiçãoPostPetStore() {
        BaseService.post(PetStoreServices.setDataRequestBodyMap());
        id = Integer.parseInt(rest().key("id").toString());
        name = rest().key("name").toString();
    }

    @Então("a requisição deve retornar os dados do Pet cadastrado na base de dados da PetStore")
    public void aRequisiçãoDeveRetornarOsDadosDoPetQueFoiCadastradoNaBaseDeDadosDaPetStore() {
        Assert.assertEquals(HttpStatus.SC_OK, rest().getResponse().statusCode());
        BaseService.assertValidSchema("schemas/petStore-schema.json");
        Assert.assertEquals(id, rest().key("id"));
        Assert.assertEquals(name, rest().key("name"));
    }

    @Dado("a necessidade de consultar os dados do Cliente da PetStore")
    public void aNecessidadeDeConsultarOsDadosDoClienteDaPetStore() {
        PetStoreServices.prepare();
        BaseService.post(PetStoreServices.setDataRequestBodyMap());
        Assert.assertEquals(HttpStatus.SC_OK, rest().getResponse().statusCode());
        id = Integer.parseInt(rest().key("id").toString());
        name = rest().key("name").toString();
    }

    @Quando("executar a requisição Get PetStore por Id")
    public void executarARequisiçãoGetPetStorePorId() {
        BaseService.get(id);
    }

    @Então("a requisição deve retornar os dados do Pet conforme o Id especificado na base de dados da PetStore")
    public void aRequisiçãoDeveRetornarOsDadosDoClienteConformeOIdEspecificadoNaBaseDeDadosDaPetStore() {
        PetStoreServices.assertResponseBodyAndStatusCodes(id, name);
    }
}
