package br.com.cotiinformatica.api_usuarios.dtos;

/*
    DTO para entrada de dados do serviço
    de cadastro de usuários
 */
public record CriarUsuarioRequest(
   String nome, //Nome do usuário
   String email, //Email do usuário
   String senha //Senha do usuário
) {
}
