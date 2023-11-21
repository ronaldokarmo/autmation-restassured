# language: pt
# charset: UTF-8

@petStore
@regressivo
Funcionalidade: Consultar Cliente na PetStore
  Eu como um usuário da PetStore
  Gostaria de consultar um registro de Cliente da PetStore
  Para manter a manutenção de dados do cliente na PetStore.

  Cenário: Validar o Cadastro de novo Pet na PteStore
    Dado a necessidade de cadastrar um novo Pet na PetStore
    Quando executar a requisição Post PetStore
    Então a requisição deve retornar os dados do Pet cadastrado na base de dados da PetStore

  Cenário: Validar a Consulta de Pet por Id na PteStore
    Dado a necessidade de consultar os dados do Cliente da PetStore
    Quando executar a requisição Get PetStore por Id
    Então a requisição deve retornar os dados do Pet conforme o Id especificado na base de dados da PetStore
