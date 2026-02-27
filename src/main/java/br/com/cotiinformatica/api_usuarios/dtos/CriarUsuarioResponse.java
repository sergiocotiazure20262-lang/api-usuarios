package br.com.cotiinformatica.api_usuarios.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/*
    DTO para saída de dados do serviço
    de cadastro de usuários
 */
public record CriarUsuarioResponse(
        UUID id, //Id do usuário
        String nome, //Nome do usuário
        String email, //Email do usuário
        String perfil, //Perfil do usuário
        LocalDateTime dataHoraCadastro //Data e hora de cadastro
) {
}
