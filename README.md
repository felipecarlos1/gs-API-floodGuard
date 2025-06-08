# 🌊 GS FloodGuard (API) 🛡

Este repositório contém uma aplicação de exemplo desenvolvida com Quarkus, focada em monitoramento de níveis de água e alertas de enchentes. A aplicação visa fornecer uma API robusta para gerenciar dados relacionados a inundações, usuários, configurações e alertas, utilizando um banco de dados para persistência.

**Renato Silva Alexandre Bezerra | RM: 560928**

**Felipe Carlos Abreu | RM: 559476**

**Jhonatan Quispe Torrez | RM: 560601**

## ✨ Funcionalidades

- **Gerenciamento de Usuários**: Cadastro, login e gerenciamento de usuários.
- **Configuração de Usuários**: Permite que os usuários configurem suas preferências e limites para alertas.
- **Monitoramento de Nível de Água**: Registro e consulta de leituras de nível de água.
- **Alertas de Enchente**: Geração e gerenciamento de alertas baseados em níveis de água configurados.
- **Histórico de Enchentes**: Registro e consulta de eventos históricos de enchentes.
- **Integração com OpenWeather**: Possível integração para obter dados climáticos externos (indicado pela presença de `OpenWeatherService.java`).

## 🛠️ Tecnologias

- **Quarkus**: Framework Java nativo para desenvolvimento de microsserviços e aplicações reativas.
- **Java**: Linguagem de programação principal.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
- **RESTful API**: Exposição de endpoints para interação com a aplicação.
- **Docker**: Suporte para conteinerização da aplicação.
- **Banco de Dados**: Persistência de dados (detalhes específicos do banco de dados não foram explicitados no `pom.xml` ou `DatabaseConfig.java` inicial, mas a estrutura sugere um banco de dados relacional).




## 📂 Estrutura do Projeto

O projeto segue uma estrutura de diretórios padrão para aplicações Quarkus, com as seguintes pastas e arquivos principais:

- `src/main/java/br/fiap/`: Contém o código-fonte principal da aplicação, organizado em pacotes:
    - `dtos`: Classes de Data Transfer Object (DTO) para comunicação entre camadas.
    - `entities`: Classes que representam as entidades do banco de dados.
    - `exceptions`: Classes para tratamento de exceções personalizadas.
    - `filters`: Filtros para requisições HTTP, como o `CorsFilter.java`.
    - `infrastructure`: Configurações de infraestrutura, como `DatabaseConfig.java`.
    - `repositories`: Classes responsáveis pela interação com o banco de dados.
    - `resources`: Endpoints da API REST (controladores).
    - `services`: Classes de serviço que contêm a lógica de negócio.
- `src/main/resources/application.properties`: Arquivo de configuração da aplicação.
- `src/main/docker`: Contém os Dockerfiles para diferentes tipos de builds (JVM, native).
- `pom.xml`: Arquivo de configuração do Maven, definindo dependências e plugins.
- `Dockerfile`: Dockerfile principal para a aplicação.




## 🚀 Como Configurar e Executar

Para configurar e executar este projeto localmente, siga os passos abaixo:

### Pré-requisitos

- Java Development Kit (JDK) 17 ou superior
- Apache Maven 3.8.x ou superior
- Docker (opcional, para execução em contêiner)
- Um banco de dados compatível (por exemplo, PostgreSQL, MySQL) e suas credenciais configuradas no `src/main/resources/application.properties`.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/felipecarlos1/gs-API-floodGuard.git
    cd gs-API-floodGuard
    ```

2.  **Configure o banco de dados:**
    Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu banco de dados.

3.  **Execute a aplicação em modo desenvolvimento:**
    ```bash
    ./mvnw quarkus:dev
    ```
    A aplicação estará disponível em `http://localhost:8080`.

4.  **Crie um build para produção (opcional):**
    ```bash
    ./mvnw package
    ```
    Isso gerará um JAR executável em `target/quarkus-app/`.

5.  **Execute a aplicação via Docker (opcional):**
    ```bash
    docker build -f src/main/docker/Dockerfile.jvm -t gs-api-floodguard-jvm .
    docker run -i --rm -p 8080:8080 gs-api-floodguard-jvm
    ```

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

