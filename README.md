# testeGerenciadorPessoaApi

  Projeto de API REST com Spring Boot
Este é um projeto de API REST implementado usando Spring Boot. A API fornece recursos para gerenciar informações de pessoas e seus endereços.



 **Endpoints disponíveis**

- GET /pessoas: retorna uma lista paginada de todas as pessoas cadastradas
- GET /pessoas/{idPessoa}: retorna uma pessoa específica pelo seu ID
- POST /pessoas: cria uma nova pessoa
- PUT /pessoas/{idPessoa}: atualiza uma pessoa existente

- GET /enderecos/{idPessoa}/pessoas: retorna uma lista de endereços de uma pessoa específica pelo seu ID
- GET /enderecos/{idPessoa}/endereco-principal: retorna o endereço principal de uma pessoa específica pelo seu ID
- POST /enderecos: adiciona um novo endereço

### Exemplos de JSON

**POST/PUT de Pessoa
json**
```
{
    "nome": "João",
    "dataNascimento": "11/08/1990"
}
```
**POST de Endereço
json**
```
{
    "logradouro": "Rua das Flores",
    "cep": "12345-678",
    "numero": "123",
    "cidade": "São Paulo",
    "enderecoPrincipal": true,
    "fkPessoa": 1
}
```
