//Classe para representar os dados (DTO - Data Transfer Object) 
//necessários para login do usuário
class LoginUsuarioDto {
    //Construtor para inicializar os atributos do DTO
    constructor(email, senha) {        
        this.email = email;
        this.senha = senha;
    }
}

//Exportando a classe para ser utilizada em outras partes da aplicação
module.exports = LoginUsuarioDto;