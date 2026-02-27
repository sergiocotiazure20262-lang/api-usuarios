package br.com.cotiinformatica.api_usuarios.dtos;

/*
    DTO para entrada de dados do serviço
    de autenticação de usuários
 */
public record AutenticarUsuarioRequest(
    String email, //Email do usuário
    String senha //Senha do usuário
) {
}
