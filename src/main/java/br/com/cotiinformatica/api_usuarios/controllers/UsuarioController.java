package br.com.cotiinformatica.api_usuarios.controllers;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    //Injeção de dependência
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("criar")
    public ResponseEntity<?> criar(@RequestBody CriarUsuarioRequest request) {

        try {
            //Enviando os dados para a camada de serviço
            var response = usuarioService.criar(request);
            //Retornar resposta de sucesso (HTTP 201 CREATED)
            return ResponseEntity.status(201).body(response);
        }
        catch(IllegalArgumentException e) {
            //Retornar a mensagem de erro (HTTP 400 BAD REQUEST)
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> autenticar(@RequestBody AutenticarUsuarioRequest request) {

        try {
            //Enviando os dados do usuário para a camada de serviços e obter o resultado
            var response = usuarioService.autenticar(request);
            //Retornar resposta de sucesso
            return ResponseEntity.status(200).body(response);
        }
        catch(IllegalArgumentException e) {
            //Retornar a mensagem de erro (HTTP 401 UNAUTHORIZED)
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
