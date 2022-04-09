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
Foram configurados Prometheus e Grafana para exportação de métricas e criação de dashboards, respectivamente.

Segue um exemplo de métricas colhidas a partir da JVM via Micrometer:

![Imagem 1](images/img1.png)
---------------------------------------------------
![Imagem 2](images/img2.png)
---------------------------------------------------
![Imagem 3](images/img3.png)