# language: pt
# charset: UTF-8

@wip
Funcionalidade: Cadastro de usuários
  Eu como um usuário admin
  Gostaria de cadastrar, editar, consultar e apagar um registro
  Para manter a manutenção do usuário.

  Cenário: Um simples GET que retorne todos os usuários
    Dado que o usuário admin está no cadastro de usuários
    Quando o usuário admin consultar a lista de todos os usuários da base
    Então deve apresentar a mensagem "sucesso"

  Cenário: Um simples POST
    Dado que o usuário admin está no cadastro de usuários
    E o usuario informa os dados desejados
    Quando o usuário salva o cadastro
    Então deve apresentar a mensagem "salvo com sucesso"

  Cenário: Um simples GET que retorne apenas um usuário
    Dado que o usuário admin está no cadastro de usuários
    E o usuario informa os dados desejados
    E o usuário salva o cadastro
    Quando o usuário admin consultar a lista de todos os usuários da base
    Então deve apresentar a mensagem "sucesso"
    E o campo "login" deve conter "asanoturna"

  Cenário: Um simples PUT que altere apenas um usuário
    Dado que o usuário admin está no cadastro de usuários
    E o usuario informa os dados desejados
    E o usuário salva o cadastro
    Quando o usuário alterar o campo "login" para "asadiurna"
    E salvar a alteração
    E o usuário admin consultar a lista de todos os usuários da base
    Então deve apresentar a mensagem "sucesso"
    E o campo "login" deve conter "asadiurna"

  Cenário: Um simples DELETE
    Dado que o usuário admin está no cadastro de usuários
    E o usuario informa os dados desejados
    E o usuário salva o cadastro
    Quando o usuário deletar o registro
    Então deve apresentar a mensagem "sucesso sem informação"

    Quando o usuário admin consultar a lista de todos os usuários da base
    Então deve apresentar a mensagem "não encontrado"

  Cenário: Validação de contrato
    Dado que o usuário admin está no cadastro de usuários
    E o usuario informa os dados desejados
    E o usuário salva o cadastro
    Quando o usuário admin consultar a lista de todos os usuários da base
    Então deve apresentar a mensagem "sucesso"
    E o contrato do usuário deve estar ok