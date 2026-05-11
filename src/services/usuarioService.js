//Importando as dependências necessárias
const bcrypt = require('bcryptjs');
const usuarioRepository = require('../repositories/usuarioRepository');
const jwt = require('jsonwebtoken');

require('dotenv').config();

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

//Função para autenticar o usuário
async function login(dto) {

    //Verificando se os campos obrigatórios estão preenchidos
    if (!dto.email || !dto.senha) {
        throw new Error('Email e senha são obrigatórios');
    }

    //Buscar o usuário no banco de dados através do email informado
    const usuario = await usuarioRepository.buscarPorEmail(dto.email);

    //Verificar se o usuário não foi encontrado
    if(!usuario) {
        throw new Error('Acesso negado. Email não encontrado.');
    }

    //Comparando a senha do banco com a senha enviada no DTO
    const senhaValida = await bcrypt.compare(dto.senha, usuario.senha);

    //Senhas não conferem
    if(!senhaValida) {
        throw new Error('Acesso negado. Credenciais inválidas.');
    }

    //Gerando o TOKEN JWT
    const token = jwt.sign(
        /* Dados do usuário */
        {
            id: usuario.id,
            nome: usuario.nome,
            email: usuario.email
        },
        /* Chave para assinatura do token (criptografia) */
        process.env.JWT_SECRET,
        /* Data de expiração do token */
        {
            expiresIn: process.env.JWT_EXPIRES_IN
        }
    );

    //Retornar os dados
    return {
        mensagem : 'Login realizado com sucesso.',
        token,
        usuario: {
            id: usuario.id,
            nome: usuario.nome,
            email: usuario.email,
            data_criacao: usuario.data_criacao
        }
    }
}

//Exportando as funções do serviço de usuário
module.exports = {
    criarUsuario,
    login
}