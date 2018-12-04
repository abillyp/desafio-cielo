# DESAFIO CIELO 
# Extrato de Lançamento em Conta

Objetivo: implementação uma API REST que irá consumir um arquivo de entrada Json e disponibilizar EndPoints para disponibilização dos dados.
Foi construída uma interface em Angular para consumo da API.

##### As seguinte tecnologias foram utilizadas:
###### BackEnd: 
- Java 8
- Spring Boot
- Mockito/MockMVC (Testes)
- Swagger (Documentação API)

###### FrontEnd:
Angular 7
BootStrap 4
PrimeNG

#####  URL Base da API : http://localhost:8080/cielo/

#####  URL Base do FrontEnd (Angular) : http://localhost:4200/

##### Documentação da API (Swagger) :  http://localhost:8080/swagger-ui.html

#### EndPoints 

GET /extrato : Retorna a lista de todas os lançamento em conta

GET /extrato/{remessa} : Retorna os lançamentos que foram efetuados para a remessa passada como parâmetro de consulta

POST /extrato/{remessa} : Inclui um novo lançamento, tendo como chave o número da remessa.

PUT /extrato/{remessa} : Altera um lançamento, tendo como chave o número da remessa.

DELETE /extrato/{remessa} : Apaga o lançamento de acordo com a remessa passada como parâmetro.
