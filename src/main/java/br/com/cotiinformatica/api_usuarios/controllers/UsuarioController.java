package br.com.cotiinformatica.api_usuarios.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @PostMapping("criar")
    public void criar() {
        //TODO Implementar
    }

    @PostMapping("autenticar")
    public void autenticar() {
        //TODO Implementar
    }

}
