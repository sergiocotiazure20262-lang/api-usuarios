//Classe de entidade para o usuário
class Usuario {
    //Método construtor para criar um novo usuário
    constructor(id, nome, email, senha, dataCriacao) {
        this.id = id; // Identificador único do usuário
        this.nome = nome; // Nome do usuário
        this.email = email; // Email do usuário (deve ser único)
        this.senha = senha; // Senha do usuário
        this.dataCriacao = dataCriacao; // Data de criação do usuário
    }   
}

//Exporta a classe Usuario para ser utilizada em outros arquivos
module.exports = Usuario;