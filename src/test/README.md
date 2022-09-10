Testes da API

Estes testes da API de Simulações foram automatizados conforme o desafio.
Todos os endpoins foram validados inicialmente no Swagger e depois utilizando as requisições do POSTMAN de maneira manual.

O código foi escrito em JAVA conforme solicitado utilizando as bibliotecas do JUNIT e Rest-Assured.

EXCECUÇÃO DOS TESTES

Os testes foram separados na past .test dentro de .src

Foi criado um pacote de testes isolados para testar cada endpoint mas de maneira que possa ser organizado como um cenário futuramente.

A classe de .SimulacoesTest foi criada dentro deste pacote para descrever o passo a passo da suíte de testes automatizados.

Então para executar os testes basta seguir os passo:

1- Importar o projeto e subir a API: mvn clean spring-boot:run

2- Para iniciar os testes automatizados deve digitar o comando:
mvn surefire:test

3- Então para ver o relatório dos testes digite o seguinte comando: allure serve target/surefire-reports

4- No navegador verifique o Status da Suite de Testes Automatizados pelo relatório da Allure.

*OBS: FIZ 2 COMENTÁRIOS SOBRE A ADERÊNCIA DE ARQUITETURA PARA ANÁLISE DO TIME:

1- Sobre a Aderencia a arquitetura da classe  RestricaoController- decisão de fluxo sendo tomadas dentro do Controller quando as regras de negócios devem ser processadas pela camada de Service
2- Aderencia a arquitetura da Classe SimulacaoController- decisão de fluxo sendo tomadas dentro do Controller quando as regras de negócios devem ser processadas pela camada de Service

*BUGS:

No caso do @Test: testSimulacaoParaOMesmoCPFStatus409 - Está com falha pois deveria retornar Status 409 mas está retornando Status 400.

-Encontrei alguns bugs ao realizar testes manuais no POSTMAN conforme as imagens em anexo na pasta BUGS ENCONTRADOS.

*RELATÓRIO ALLURE:

-Inseri um print da tela com os resultados ao finalizar a suite de testes como exemplo de validação de status e categoria dos testes.


