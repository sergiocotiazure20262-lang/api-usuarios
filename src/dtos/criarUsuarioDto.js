//Classe para representar os dados (DTO - Data Transfer Object) 
//necessários para criar um usuário
class CriarUsuarioDto {
    //Construtor para inicializar os atributos do DTO
    constructor(nome, email, senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}

//Exportando a classe para ser utilizada em outras partes da aplicação
module.exports = CriarUsuarioDto;