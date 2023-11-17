# PROJETO MODELO PARA TESTE API

Projeto desenvolvido com proposito de ser um modelo base para teste de API

#### Versão: 1.2

[![CucumberReports: Aproved](https://messages.cucumber.io/api/report-collections/c379d9ee-2064-438c-b63a-a035994a922d/badge)](https://reports.cucumber.io/report-collections/c379d9ee-2064-438c-b63a-a035994a922d)

![](src/test/resources/icon.png)

## PRÉ-REQUISITOS

Requisitos de software e hardware necessários para executar este projeto de automação

* Java 11 JDK
* Maven 3.5.*
* Intellij IDE
* Plugins do Intellij
    * Cumcuber for java
    * Lombok
    * Ideolog
* Docker
* Docker Compose

## ESTRUTURA DO PROJETO

| Diretório                         | finalidade                                                                                                 | 
|--------------------------------|------------------------------------------------------------------------------------------------------------|
| src\main\java\constants           | atributos constantes para facil compartilhamento e atualização                                              |
| src\main\java\services               | Local onde deve ser criado os objetos que executam requisições e validações das respotas                   |
| src\main\java\support             | Metodos genéricos que apoiam as classes de testes                                                           |
| src\test\java\hooks             | Metodos que executam antes e depois de cada teste (@Before, @After)                                        |
| src\test\java\runner             | Metodo prinicipal que inicia os testes via cucumber                                                         |
| src\test\java\steps              | Local onde deve ser criado as classes que representam os steps definition do cucumber                         |
| src\test\resources\data         | Massa de dados segregada por ambiente, escritos em arquivos yaml                                            |
| src\test\resources\features      | Funcionalidade e cenarios de teste escritos em linguagem DSL (Gherkin language)                               | 

## SNIPPETS

### RUNNER

```java

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@JIRA-001"},
        glue = {"hooks", "steps"},
        plugin = {"io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm",
                "pretty", "json:target/json-cucumber-reports/cucumber.json",
                "junit:target/xml-junit/junit.xml"},
        features = {"src/test/resources/features"})
public class Runner {
    @AfterClass
    public static void tearDownAll() {
        new ActionController().sendResults();
    }
}


```

### HOOKS

```java

@Log4j2
public class Hook extends Context {

    @Before
    public void beforeScenario(Scenario scenario) {
        setScenario(scenario);
        log.info(String.format("TESTE INICIADO: %s", getScenario().getName()));
        rest().setBaseURI();
    }

    @AfterStep
    public void afterStep() {
        report().setText(rest().getAllLogs());
    }


    @After
    public void afterScenario() {
        rest().quit();
        log.info(String.format("TESTE FINALIZADO: %s", getScenario().getName()));
        log.info(String.format("TESTE STATUS: %s", getScenario().getStatus()));
    }
}
```

### FEATURES

```gherkin
# language: pt
# charset: UTF-8

@JIRA-001
Funcionalidade: Usuarios
  Eu como cliente gostaria de consultar os usuário do cadastro ativo

  Cenário: Consultar usuario da base ativa
    Quando o cliente consultar um usuario da base
    Entao deve apresentar os dados do usuario
```

### SERVICES

```java
public class UserService implements EndPoints {

    public void get() {
        rest().newRequest();
        rest().setResponse(rest().getRequest().get(USER));
    }

    public void verifyUser() {
        rest()
                .getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", containsString("Leanne Graham"));
    }

}
```

### STEP DEFINITIONS

```java
public class UserSteps {
    private UserService userService;

    public UserSteps() {
        userService = new UserService();
    }

    @Quando("o cliente consultar um usuario da base")
    public void oClienteConsultarUmUsuarioDaBase() {
        userService.get();
    }

    @Entao("deve apresentar os dados do usuario")
    public void deveApresentarOsDadosDoUsuario() {
        userService.verifyUser();
    }


}
```

## DOWNLOAD DO PROJETO TEMPLATE PARA SUA MÁQUINA LOCAL

Faça o donwload do template no repositório de código para utilizar no seu projeto em especifico, feito isso, fique a
vontande para usufruir dos recursos disponíveis e também customizar de acordo com sua necessidade.

## FRAMEWORKS UTILIZADOS

Abaixo está a lista de frameworks utilizados nesse projeto

* Jackson - Responsável pela leitura de dados de arquivo yaml file
* Gson - Responsável pela serializacao e deserializacao de objetos
* Allure - Responsável pelo report em HTML
* Java Faker - Responsável pela geracao de dados sintéticos
* Rest Assured - Responsável pelos interação com a camada HTTP para teste de API (Json, Soap, Xml)
* Cucumber - Responsável pela especificação executável dos cenários
* AssertJ - Especializado em validações com mais tipos e formatos de verificação
* Lombok - Otimizacao de classes modelos
* Log4j - Responsável pelo Log do projeto

## COMANDO PARA EXECUTAR OS TESTES

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para
rodar os testes automatizados.

```
mvn clean test
```

## COMANDO PARA GERAR EVIDÊNCIAS EM HTML (ALLURE)

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para
gerar as evidências em HTML

```
mvn allure:report
```

## MULTIPLOS COMANDOS

Você também pode mesclar a linha de comando maven com options do cucumber,
sendo assim você pode escolher uma determinada tag que se deseja executar do cucumber,
podendo escolher também a massa de dados que irá utilizar e juntamente aplicar o linha de comando para gerar o report
HTML.

```
mvn clean test -Dcucumber.filter.tags=@Tag -Denv=des allure:report
```

## RETENTATIVAS

O Projeto por padrão vai reexecutar os testes falhados novamente, somente os testes que falharam anteriormente,
a quantidade de vezes de retentiva pode ser configurado, por padrão será executado novamente 1 vez, se você
quiser aumentar o numero de tentativas você deve passar via linha de comando

```shell
-Dretry={Numero de tentativas} 
```

```shell
mvn clean test -Dcucumber.filter.tags=@positivo -Dretry=2 -Denv=des -Dbrowser=chrome -Dheadless=true
```

### Detalhes dos comandos

| Comando                      | Função                                                                                                   | 
|------------------------------    |---------------------------------------------------------------------------------------------------------- |
| -Dcucumber.filter.tags=@Tag | Sobrescreve os parametros do cucumber, neste exemplo estou filtrado os teste pela tag @dev, então somente os cenários com esta tag irão executar                 |
| -Denv=des       | Seleciona qual massa de teste de acordo com o ambiente que vai ser utilizado, exemplo: des, prod ou uat              |
| -Dretry=1                   | Execute os testes que falharam novamente, sendo somente os que falharam como uma nova tentativa                                                  |

## DOCKER

Docker pode ser utilizado para executar scripts de testes

### COMANDOS

Construir a imagem docker

```
docker build -t <IMAGE-NAME> -f ./Dockerfile .
```

Executar os testes com docker

```
docker run -v "$PWD/target:/usr/target" <IMAGE-NAME>:latest mvn test -Dcucumber.filter.tags=@Tag -Denv=des
```

## ALLURE SERVER

Subir servidor Allure Report para compartilhar em uma rede interna

dentro da pasta docker com ferramenta de linha de comando

```
docker-compose up -d allure allure-ui
```

```yaml
version: '3'

services:
  allure:
    image: "frankescobar/allure-docker-service"
    environment:
      CHECK_RESULTS_EVERY_SECONDS: 1
      KEEP_HISTORY: 1
      KEEP_HISTORY_LATEST: 10
    ports:
      - "5050:5050"
  allure-ui:
    image: "rubenslobo/allure-server-ui:latest"
    environment:
      ALLURE_DOCKER_PUBLIC_API_URL: "http://localhost:5050"
      ALLURE_DOCKER_PUBLIC_API_URL_PREFIX: ""
    ports:
      - "5252:5252"
```

Para habilitar a integração você deve popular o arquivo de properties localizado "src/test/resources/allure.properties"

```properties
allure.server.enable=false
allure.results.directory=target/allure-results
allure.server.host=http://localhost
allure.server.port=5050
allure.server.project=demo
allure.server.force_project_creation=true
```

## EVIDÊNCIAS

Os arquivos com as evidências ficam localizados na pasta target do projeto, esta pasta só é criada depois da primeira
execução.

```
 Report HTML: target\site\index.html
 Json Cucumber: target\json-cucumber-reports\cucumber.json
 Xml Junit: tagert\xml-junit\junit.xml
```

Ps.: Caso você necessite utilizar do Allure, o mesmo somente cria os relatórios pelo maven com o paramêtro 'allure:
report', conforme exemplo de múltiplos comandos acima.

## LOG DE EXECUÇÃO

Os logs de execução gerados pelo Log4j2 ficam alocados na pasta target/log

## LINKS DE APOIO

* [RestAssured Documentação oficial](https://github.com/rest-assured/rest-assured/wiki/Usage)
* [Converter JSON para Classe Java](https://www.jsonschema2pojo.org/)
* [Converter JSON para Yaml](https://www.json2yaml.com/)
