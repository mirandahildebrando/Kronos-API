# Kronos - Sistema de PDV para Empresas

Projeto backend do sistema Kronos, que serve como API para o PDV, controle de estoque e login de usuários.  
O projeto é dividido em módulos dentro do mesmo projeto para facilitar manutenção e expansão.

---

## Estrutura do projeto

Kronos/
│
├─ Login/ # Módulo de autenticação e gerenciamento de usuários
│ ├─ controller/
│ ├─ service/
│ ├─ repository/
│ ├─ model/
│ └─ dtos/
│
├─ PDV/ # Módulo do ponto de venda (em desenvolvimento)
│ ├─ controller/
│ ├─ service/
│ ├─ repository/
│ ├─ model/
│ └─ dtos/
│
├─ Estoque/ # Módulo de controle de estoque (em desenvolvimento)
│ ├─ controller/
│ ├─ service/
│ ├─ repository/
│ ├─ model/
│ └─ dtos/
│
├─ pom.xml
└─ src/main/resources/application.properties


---

## Tecnologias usadas

- Java 17
- Spring Boot
- PostgreSQL
- Maven
- Swagger (para testes da API)
- Jakarta Validation (para validação de campos)

---

## Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/kronos.git
Entre na pasta do projeto:

cd kronos
Configure o banco de dados PostgreSQL no arquivo src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
Rode o projeto:

mvn spring-boot:run
Abra o Swagger para testar a API:

http://localhost:8080/swagger-ui/index.html
Endpoints do módulo Login
Criar usuário
POST /users
Request:

{
  "username": "meuusuario",
  "password": "minhasenha123"
}
Validações:

username: obrigatório, 5-30 caracteres

password: obrigatório, 8-20 caracteres

Listar todos usuários
GET /users

Buscar usuário por ID
GET /users/byId?id={id}

Atualizar usuário
PUT /users
Request:

{
  "id": 1,
  "username": "usuarioatualizado",
  "password": "novasenha123"
}
Deletar usuário
DELETE /users/{id}

Como organizar novos módulos (PDV, Estoque)
Crie uma pasta dentro de Kronos/ com o nome do módulo (ex: PDV/ ou Estoque/).

Dentro do módulo, siga a mesma estrutura:

controller/
service/
repository/
model/
dtos/
Crie as classes e endpoints seguindo o padrão do módulo Login.

Adicione os endpoints ao Swagger (Springfox ou Springdoc) para testar.

Boas práticas
Valide sempre os campos obrigatórios antes de enviar ao backend.

Senhas devem ter no mínimo 8 caracteres e máximo 20.

Username deve ter no mínimo 5 caracteres e máximo 30.

Mantenha os módulos separados para facilitar manutenção e testes.
