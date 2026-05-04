//Importando as dependências necessárias
const bcrypt = require('bcryptjs');
const usuarioRepository = require('../repositories/usuarioRepository');

//Função para criar um novo usuário
async function criarUsuario(dto) {

    //Verificando se os campos obrigatórios estão preenchidos
    if (!dto.nome || !dto.email || !dto.senha) {
        throw new Error('Nome, email e senha são obrigatórios');
    }

    //Buscando o usuário pelo email para verificar se já existe um usuário com o mesmo email
    const usuarioExistente = await usuarioRepository.buscarPorEmail(dto.email);

    //Se o usuário já existir, lançamos um erro
    if (usuarioExistente) {
        throw new Error('Já existe um usuário com esse email');
    }

    //Criptografando a senha do usuário
    const senhaCriptografada = await bcrypt.hash(dto.senha, 10);

    //Criando o objeto do usuário a ser salvo no banco de dados
    const usuario = {
        nome: dto.nome,
        email: dto.email,
        senha: senhaCriptografada
    }

    //Salvando o usuário no banco de dados e retornando o resultado
    return await usuarioRepository.criar(usuario);
}

//Exportando as funções do serviço de usuário
module.exports = {
    criarUsuario
}