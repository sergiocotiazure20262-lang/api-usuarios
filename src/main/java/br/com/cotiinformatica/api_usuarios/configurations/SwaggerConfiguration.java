package br.com.cotiinformatica.api_usuarios.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    /*
        Método para configurar a documentação
     */
    @Bean
    OpenAPI customOpenApi() {
        var openAPI = new OpenAPI()
                .info(new Info()
                        .title("Usuarios API - COTI Informática")
                        .description("API Spring Boot com Spring Data JPA e JWT para gerenciamento de usuários")
                        .version("v1"));

        return openAPI;
    }

}