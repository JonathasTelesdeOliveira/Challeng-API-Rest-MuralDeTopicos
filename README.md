# 📌 Challenge API REST – Mural de Tópicos

API REST desenvolvida em **Java com Spring Boot**, com foco em **boas práticas de arquitetura**, **segurança com JWT**, **persistência com JPA/Hibernate** e **organização em camadas**, simulando um **mural de tópicos** semelhante a fóruns de discussão.

O projeto foi estruturado seguindo padrões utilizados no mercado, com separação clara de responsabilidades, facilitando manutenção, testes e evolução futura.

---
![Badge do projeto](docs/img/Badge-Spring.png)

![Diagram do projeto](docs/img/img.png)


## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - Spring Security
- **JWT (JSON Web Token)** – autenticação stateless
- **Hibernate**
- **DOCKER**
- **MySQL**
- **Flyway** – versionamento de banco de dados
- **Maven**
- **Lombok**
- **Postmam** – Ferramenta para teste api

---

## 🧱 Arquitetura do Projeto

As Pastas do projeto segue uma **arquitetura em camadas**, inspirada em **Clean Architecture + DDD leve**, organizada da seguinte forma:

```text
src/main/java
└── com.seupacote.challengapirestmuraldetopicos
    ├── application
    │   ├── controller
    │   └── dto
    │       ├── request
    │       └── response
    │
    ├── domain
    │   ├── model
    │   └── repository
    │
    ├── service
    │
    ├── infrastructure
    │   ├── security
    │   ├── exception
    │   └── converter
    │
    └── ChallengApiRestMuralDeTopicosApplication.java

_______________________________________________________________________________________________

📂 Descrição das Camadas
🔹 application

Camada responsável pela entrada de dados da aplicação.

Controllers REST

DTOs de entrada (request) e saída (response)

Não contém regra de negócio

_______________________________________________________________________________________________


🔹 domain

Camada de negócio puro.

Entidades (Topico, Usuario)

Enums (StatusTopico)

Interfaces de repositório

Esta camada não depende de nenhuma outra.

_______________________________________________________________________________________________

🔹 service

Camada responsável pelas regras de negócio.

Validações

Orquestração dos fluxos

Comunicação com repositórios

Aplicação das regras do domínio

_______________________________________________________________________________________________

🔹 infrastructure

Camada de detalhes técnicos e transversais.

Segurança (JWT, filtros e configurações)

Tratamento global de exceções

Conversores de entidades ↔ DTOs

_______________________________________________________________________________________________

🔐 Segurança

A autenticação é feita via JWT (Bearer Token).

🔑 Fluxo de autenticação:

1. Usuário realiza login (/login)

2. API gera um token JWT

3. Token é enviado no header:

Authorization: Bearer <token>

4. Filtro de segurança valida o token a cada requisição

A aplicação é stateless, sem uso de sessão.

_______________________________________________________________________________________________

📌 Endpoints Principais
🔸 Autenticação

POST /login – autentica o usuário e gera o token JWT

🔸 Tópicos

POST /topicos – cadastrar tópico

GET /topicos – listar todos os tópicos

GET /topicos/{id} – buscar tópico por ID

PUT /topicos/{id} – atualizar tópico

DELETE /topicos/{id} – remover tópico

GET /topicos/cursoAno?curso=JAVA&ano=2024 – filtrar por curso e ano

Endpoints protegidos exigem token JWT válido.

_______________________________________________________________________________________________

🗄️ Banco de Dados

° MySQL

° Versionamento de schema com Flyway

° Scripts localizados em:

_______________________________________________________________________________________________
⚙️ Configuração
application.properties (exemplo)

spring.datasource.url=jdbc:mysql://localhost:3306/NomeDoBancoDeDados
spring.datasource.username=root   <-Exemplo nome do usuário
spring.datasource.password=senha    <-Exemplo senha 

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

api.security.token.secret=123456789  <-Exemplo senha no banco vai ser convertida para -
 Hash Bcrypt: 
$2a$12$RAdO8UAV1A1n0zCtl1tBWedeZV/dM8QaQM3bVW0E8wBuPlO9F2xia

_______________________________________________________________________________________________


🎯 Objetivo do Projeto

Este projeto tem como objetivo:

Consolidar conhecimentos em Spring Boot

Aplicar boas práticas de arquitetura

Implementar segurança com JWT

Servir como portfólio técnico para vagas de estágio/júnior

_______________________________________________________________________________________________


👨‍💻 Autor

Jonathas Teles
Desenvolvedor Java | Spring Boot
Estudante de Análise e Desenvolvimento de Sistemas
Projeto elaborado no desafio Oracle One & Alura

_______________________________________________________________________________________________


📄 Licença

Este projeto é apenas para fins educacionais.

_______________________________________________________________________________________________
