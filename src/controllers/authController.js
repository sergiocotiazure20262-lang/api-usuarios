const usuarioService = require('../services/usuarioService');
const LoginUsuarioDTO = require('../dtos/loginUsuarioDto');

//Função para login do usuário
async function login(req, res) {
    try {
        
        //Capturando os dados do corpo da requisição e criando um DTO
        const dto = new LoginUsuarioDTO(
            req.body.email, //Capturando o email do usuário
            req.body.senha //Capturando a senha do usuário
        );
        
        //Chamando o serviço de autenticação
        const resultado = await usuarioService.login(dto);

        //Retornar resposta de sucesso (HTTP 200)
        return res.status(200).json(resultado);
    }
    catch(error) {
        return res.status(400).json({
            erro: error.message
        })
    }
}

module.exports = {
    login
};