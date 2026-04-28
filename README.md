# Kronos API - Sistema de Gestão (PDV + Estoque)

API REST desenvolvida em Java com Spring Boot para gerenciamento de usuários, produtos e vendas (PDV), com controle de estoque e regras de negócio.

A aplicação foi projetada com foco em uso real, seguindo boas práticas de arquitetura em camadas e padronização de respostas.

---

## 🌐 Ambiente

API disponível em produção:

👉 https://kronos-api-ck9x.onrender.com

Documentação Swagger:

👉 https://kronos-api-ck9x.onrender.com/swagger-ui/index.html

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Docker
- OpenAPI / Swagger

---

## 🧩 Arquitetura

A aplicação segue arquitetura em camadas:


controller → service → repository


Separação por responsabilidade:

- Controller: entrada/saída da API
- Service: regras de negócio
- Repository: acesso ao banco
- DTOs: transporte de dados
- Entities: persistência

---

## 📌 Módulos da API

### 🔐 Autenticação
- `POST /auth/login`

---

### 👤 Usuários
- `POST /users`
- `GET /users`
- `GET /users/byId?id={id}`
- `PUT /users/{id}`
- `DELETE /users/reset`

---

### 📦 Produtos
- `POST /products`
- `GET /products`
- `GET /products/{id}`
- `PUT /products/{id}`
- `DELETE /products/{id}`

---

### 💰 Vendas (PDV)
- `POST /sales`
- `GET /sales`
- `GET /sales/{id}`
- `PUT /sales/{id}`
- `DELETE /sales/{id}`

---

## ⚙️ Regras de negócio

- Controle de estoque em tempo real
- Bloqueio de venda com estoque insuficiente
- Atualização automática do estoque após venda
- Cálculo automático do valor total da venda
- Estrutura baseada em DTO para desacoplamento

---

## 📤 Padrão de resposta

A API utiliza um padrão consistente de retorno:

```json
{
  "data": {},
  "message": "Mensagem da operação"
}
📦 Exemplo de requisição (Venda)
POST /sales

{
  "paymentMethod": "PIX",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
📥 Exemplo de resposta
{
  "data": {
    "paymentMethod": "PIX",
    "totalValue": 100.0,
    "items": [
      {
        "productId": 1,
        "quantity": 2
      }
    ]
  },
  "message": "Venda criada com sucesso"
}
🧪 Execução local
git clone https://github.com/mirandahildebrando/Kronos-API
cd kronos-api
mvn spring-boot:run
🐳 Docker
docker build -t kronos-api .
docker run -p 8080:8080 kronos-api
