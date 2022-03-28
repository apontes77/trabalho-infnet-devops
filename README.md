# trabalho-infnet-devops

Esta aplicação foi criada como entregável da disciplina de Processos Ágeis e DevOps, da pós-graduação MIT em Engenharia de Software no Instituto INFNET.

Para executar localmente esta API REST, certifique-se que tenha instalado:
- Java 17
- Docker
- IDE de sua preferência

Para executar o Spring Boot, digite no terminal no diretório do projeto:

~~~bash
docker-compose up -d
~~~

e logo após:

~~~bash
./gradlew bootRun
~~~

Para executar os testes:
~~~bash
./gradlew clean test
~~~

Para executar análise estática com spotbugs:
~~~bash
./gradlew clean check
~~~
