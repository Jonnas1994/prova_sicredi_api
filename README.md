# prova_sicredi_api
Prova técnica de Automação de Testes - API

## Como Executar?
- Execute a suite de teste de forma manual ou executando o seguitne comando:

```
$ mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/Testsuites/ApiInscritos.xml
```


- Após executar os testes, execute o comando abaixo para gerar o relatório no Allure

```
$ mvn -Dtest=ReportGenerate#run test
```