# PetResgate

Sistema de gerenciamento de resgates e adoções de animais, desenvolvido com Spring Boot.

## 📌 Funcionalidades

- Cadastro de usuários e login.
- Registro de animais para adoção ou resgate.
- Comentários em publicações de animais.
- Endereços associados aos usuários.
- API REST documentada via Postman.

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- Maven
- Docker
- PostgreSQL

## 📁 Estrutura do Projeto

```
petresgate-master/
├── src/main/java/br/com/ifba/petresgate/
│   ├── domain/            # Entidades e DTOs
│   ├── exception/         # Exceções personalizadas
│   ├── mapper/            # Conversores entre entidades e DTOs
│   └── PetresgateApplication.java
├── collection/
│   └── Animal API.postman_collection.json
├── docker-compose.yml     # Configuração do Docker
├── pom.xml                # Dependências Maven
```

## 🧪 Como Executar

### Pré-requisitos

- Java 17+
- Docker e Docker Compose (opcional)
- Maven

### Clonar o repositório

```bash
git clone https://github.com/LRz00/petresgate.git
cd petresgate
```

### Rodar com Maven

```bash
./mvnw spring-boot:run
```

### Rodar com Docker

```bash
docker-compose up --build
```

## 🔗 Coleção Postman

A coleção Postman para testar a API está disponível em:

```
collection/Animal API.postman_collection.json
```

Importe-a no Postman para facilitar os testes.
