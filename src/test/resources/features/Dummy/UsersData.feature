# language: pt
# charset: UTF-8

@userData
@regressivo
Funcionalidade: Controle de usuários da Rede Social Dummy
  Eu como administrador  da Rede Social
  Gostaria de controlar dados dos usuários da Rede Social
  Para incluir, alterar e deletar os usuários da Rede Social.

  Cenário: Validar a Inclusão de Novo Usuário
    Dado a necessidade de incluir novo usuário na Rede Social
    Quando executar a requisição da API Post Create User
    Então a requisição deve retornar os dados do novo usuário cadastrado na base da Rede Social

  Cenário: Validar a Consulta de Usuários
    Dado a necessidade de consultar todos os dados dos usuários da Rede Social
    Quando executar a requisição da API Get List Users
    Então a requisição deve retornar os dados de todos os usuários cadastrados na base da Rede Social
