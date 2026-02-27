package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioResponse;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import br.com.cotiinformatica.api_usuarios.enums.Perfil;
import br.com.cotiinformatica.api_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

    //Injeção de dependência
    @Autowired
    private UsuarioRepository usuarioRepository;

    /*
        Método para realizar o serviço de cadastro do usuário
     */
    public CriarUsuarioResponse criar(CriarUsuarioRequest request) {

        //Validar os campos
        validarNome(request.nome());
        validarEmail(request.email());
        validarSenha(request.senha());

        //Criando um objeto da classe Usuario (entidade)
        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(criptografar(request.senha()));
        usuario.setPerfil(Perfil.USUARIO_COMUM);
        usuario.setDataHoraCadastro(LocalDateTime.now());

        //Salvar no banco de dados
        usuarioRepository.save(usuario);

        //Retornar os dados do usuário
        return new CriarUsuarioResponse(
             usuario.getId(), //Id do usuário
             usuario.getNome(), //Nome do usuário
             usuario.getEmail(), ///Email do usuário
             usuario.getPerfil().toString(), //Nome do perfil
             usuario.getDataHoraCadastro() //Data e hora do cadastro
        );
    }

    //Método privado para validar o nome do usuário
    private void validarNome(String nome) {
        //Verificar se o nome esta vazio ou tem menos de 6 caracteres
        if(nome == null || nome.trim().length() < 6) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório e deve conter pelo menos 6 caracteres.");
        }
    }

    //Método para validar o email do usuário
    private void validarEmail(String email) {
        //Verificar se o email esta vazio
        if(email == null || email.trim().isEmpty()) {
            throw  new IllegalArgumentException("O email do usuário é obrigatório.");
        }
        //Verificar se o email já esta cadastrado no banco de dados
        if(usuarioRepository.findByEmail(email) != null) {
            throw  new IllegalArgumentException("O email informado já esta cadastrado. Tente outro.");
        }
    }

    //Método para validar a senha do usuário
    private void validarSenha(String senha) {
        //Verificar se a senha esta vazio
        if(senha == null || senha.trim().isEmpty()) {
            throw  new IllegalArgumentException("A senha do usuário é obrigatória.");
        }

        //Aplicando uma expressão regular (REGEX) para verificar se a senha é forte
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        boolean senhaValida = Pattern.matches(regex, senha);

        if (!senhaValida) {
            throw  new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial.");
        }
    }

    //Método utilizado para criptografar a senha
    private String criptografar(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = md.digest(senha.getBytes());

            // Converter para hexadecimal
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }

}
