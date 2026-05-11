const express = require('express');
const cors = require('cors');
const swaggerUi = require('swagger-ui-express');

const usuarioRoutes = require('./routes/usuarioRoutes');
const swaggerDocument = require('./docs/swagger');

//Criando a aplicação Express
const app = express();

//Configuração CORS
app.use(
   cors({
       origin: 'http://localhost:4200',
       methods: ['GET', 'POST', 'PUT', 'DELETE'],
       allowedHeaders: ['Content-Type', 'Authorization'] 
   })     
);

//Middleware para parsear o corpo das requisições como JSON
app.use(express.json());

//Rota para documentação do Swagger
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));

//Registrando as rotas de usuário na aplicação
app.use('/api', usuarioRoutes);

//Exportando as configurações
module.exports = app;
