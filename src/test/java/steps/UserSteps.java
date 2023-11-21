package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import org.testng.Assert;
import services.UserService;
import support.JsonUtils;
import support.data.DataYaml;

import java.util.HashMap;

import static support.context.Context.rest;

public class UserSteps {

    private final UserService userService = new UserService();
    private final HashMap<String, Object> json = new HashMap<>();
    private HashMap data;

    @Dado("que o usuário admin está no cadastro de usuários")
    public void queOUsuarioAdminEstaNoCadastroDeUsuarios() {
        userService.prepare();
        data = DataYaml.getMapYamlValues("Usuarios", "usuarios");
    }

    @Quando("o usuário admin consultar a lista de todos os usuários da base")
    public void oUsuarioAdminConsultarAListaDeTodosOsUsuariosDaBase() {
        String id = userService.getId();
        if (id.equals("0")) { id = ""; }
        userService.get(id);
    }

    @E("que o usuário queira ver o user numero {string}")
    public void queOUsuarioQueiraVerOUserNumero(String numero) {
        userService.setId(numero);
    }

    @E("o usuario informa os dados desejados")
    public void oUsuarioInformaOsDadosDesejados() {
        json.put("login",data.get("login"));
        json.put("full_name", data.get("full_name"));
        json.put("email", data.get("email"));
        json.put("age", data.get("age"));
    }

    @Quando("o usuário salva o cadastro")
    public void oUsuarioSalvaOCadastro() {
        userService.post(json);
        userService.setId(rest().key("id").toString());
    }

    @E("salvar a alteração")
    public void salvarAAlteracao() {
        userService.put(json, userService.getId());
    }

    @Quando("o usuário alterar o campo {string} para {string}")
    public void oUsuarioAlterarOCampoPara(String field, String value) {
        json.put(field, value);
    }

    @Quando("o usuário deletar o registro")
    public void oUsuarioDeletarORegistro() {
        userService.delete(userService.getId());
    }

    @E("o contrato do usuário deve estar ok")
    public void oContratoDoUsuarioDeveEstarOk() {
        boolean result = JsonUtils.checkSchema(rest().getResponse().body().jsonPath().prettyPrint(), "schemas/user-schema.json");
        Assert.assertTrue(result, "Esquema diferente do esperado");
    }
}
