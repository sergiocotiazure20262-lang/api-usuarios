package br.com.cotiinformatica.api_usuarios.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/*
    DTO para saída de dados do serviço
    de autenticação de usuários
 */
public record AutenticarUsuarioResponse(
        UUID id, //Id do usuário
        String nome, //Nome do usuário
        String email, //Email do usuário
        String perfil, /// Perfil do usuário
        LocalDateTime dataHoraAcesso, //Data e hora do acesso
        String accessToken //Token de autenticação
) {
}
