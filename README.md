# Personal Library API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de uma biblioteca pessoal.

O sistema permite cadastrar, consultar, atualizar e excluir livros, armazenando informações como título, autor, gênero, ano de publicação e status de leitura.

A aplicação utiliza **PostgreSQL** para persistência dos dados e **Spring Data JPA / Hibernate** para comunicação com o banco de dados. Também foram implementadas validações dos dados recebidos e tratamento de exceções com respostas HTTP adequadas.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- PostgreSQL
- Maven
- Postman
- IntelliJ IDEA

## Funcionalidades

A API permite realizar as principais operações de gerenciamento de livros:

- Cadastrar um novo livro
- Listar todos os livros cadastrados
- Consultar um livro pelo ID
- Atualizar as informações de um livro
- Excluir um livro
- Validar os dados enviados nas requisições
- Retornar erro adequado quando o livro não for encontrado

## Endpoints

| Método | Endpoint | Descrição | Retorno esperado |
|---|---|---|---|
| POST | `/livros` | Cadastra um novo livro | `201 Created` |
| GET | `/livros` | Lista todos os livros | `200 OK` |
| GET | `/livros/{id}` | Consulta um livro pelo ID | `200 OK` |
| PUT | `/livros/{id}` | Atualiza um livro existente | `200 OK` |
| DELETE | `/livros/{id}` | Exclui um livro | `204 No Content` |

### Tratamento de Erros

- `400 Bad Request` — dados inválidos enviados na requisição
- `404 Not Found` — livro não encontrado

## Exemplos de Uso

### Cadastrar Livro

**Requisição:**

`POST /livros`

**Corpo da requisição:**

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "genero": "Tecnologia",
  "anoPublicacao": 2008,
  "statusLeitura": "LIDO"
}
```

**Resposta esperada:**

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "genero": "Tecnologia",
  "anoPublicacao": 2008,
  "statusLeitura": "LIDO"
}
```

**Status:** `201 Created`

### Listar Livros

**Requisição:**

`GET /livros`

**Resposta esperada:**

```json
[
  {
    "id": 1,
    "titulo": "Clean Code",
    "autor": "Robert C. Martin",
    "genero": "Tecnologia",
    "anoPublicacao": 2008,
    "statusLeitura": "LIDO"
  }
]
```

**Status:** `200 OK`

### Consultar Livro por ID

**Requisição:**

`GET /livros/1`

**Resposta esperada:**

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "genero": "Tecnologia",
  "anoPublicacao": 2008,
  "statusLeitura": "LIDO"
}
```

**Status:** `200 OK`

### Atualizar Livro

**Requisição:**

`PUT /livros/1`

**Corpo da requisição:**

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "genero": "Tecnologia",
  "anoPublicacao": 2008,
  "statusLeitura": "LENDO"
}
```

**Resposta esperada:**

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "genero": "Tecnologia",
  "anoPublicacao": 2008,
  "statusLeitura": "LENDO"
}
```

**Status:** `200 OK`

### Excluir Livro

**Requisição:**

`DELETE /livros/1`

**Status:** `204 No Content`

### Erro 404 - Livro Inexistente

**Requisição:**

`GET /livros/999`

**Resposta esperada:**

```json
{
  "message": "Livro não encontrado"
}
```

**Status:** `404 Not Found`

### Erro 400 - Validação

**Requisição:**

`POST /livros`

**Corpo da requisição:**

```json
{
  "titulo": "",
  "autor": "",
  "genero": "",
  "anoPublicacao": -1,
  "statusLeitura": ""
}
```

**Resposta esperada:**

```json
{
  "titulo": "O título é obrigatório",
  "autor": "O autor é obrigatório",
  "genero": "O gênero é obrigatório",
  "anoPublicacao": "O ano de publicação deve ser maior que zero",
  "statusLeitura": "O status de leitura é obrigatório"
}
```

**Status:** `400 Bad Request`

## Como Executar o Projeto

### Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- Java 17
- PostgreSQL
- Git
- IntelliJ IDEA
- Postman

### 1. Clonar o repositório

```bash
git clone https://github.com/gumautoni/personal-library-api.git
```

Depois, entre na pasta do projeto:

```bash
cd personal-library-api
```

### 2. Criar o banco de dados

No PostgreSQL, crie um banco com o nome:

```text
personal_library
```

Configuração utilizada:

```text
Host: localhost
Porta: 5432
Banco: personal_library
Usuário: postgres
```

### 3. Configurar a senha do PostgreSQL

A senha do banco não fica armazenada diretamente no código.

No PowerShell, antes de iniciar a aplicação, defina a variável de ambiente:

```powershell
$env:DB_PASSWORD="SUA_SENHA_DO_POSTGRESQL"
```

Substitua `SUA_SENHA_DO_POSTGRESQL` pela senha configurada para o usuário `postgres`.

### 4. Executar a aplicação

No terminal, dentro da pasta do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Quando a aplicação iniciar corretamente, será exibida uma mensagem semelhante a:

```text
Tomcat started on port 8080
Started PersonalLibraryApiApplication
```

A API ficará disponível em:

```text
http://localhost:8080
```

### 5. Testar a API

Com a aplicação em execução, os endpoints podem ser testados utilizando o Postman.

Exemplo:

```text
GET http://localhost:8080/livros
```

## Estrutura do Projeto

```text
src/main/java/com/gumautoni/personallibraryapi
├── controller
│   └── LivroController.java
├── dto
│   └── LivroRequest.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── LivroNotFoundException.java
├── model
│   └── Livro.java
├── repository
│   └── LivroRepository.java
├── service
│   └── LivroService.java
└── PersonalLibraryApiApplication.java
```

### Responsabilidades

- `controller` — recebe as requisições HTTP e disponibiliza os endpoints
- `dto` — recebe e valida os dados das requisições
- `exception` — realiza o tratamento de erros
- `model` — representa a entidade `Livro`
- `repository` — realiza o acesso aos dados utilizando Spring Data JPA
- `service` — concentra a lógica da aplicação

## Fluxo da Aplicação

```text
Cliente / Postman
       ↓
LivroController
       ↓
LivroService
       ↓
LivroRepository
       ↓
JPA / Hibernate
       ↓
PostgreSQL
```

## Banco de Dados

A aplicação utiliza **PostgreSQL** para persistência dos dados.

Banco utilizado:

```text
personal_library
```

A entidade `Livro` é mapeada pelo JPA/Hibernate para a tabela:

```text
livros
```

Para consultar os registros diretamente no PostgreSQL:

```sql
SELECT * FROM livros;
```

## Objetivo Acadêmico

Projeto desenvolvido como atividade acadêmica com o objetivo de aplicar, na prática, conceitos de:

- Java
- Programação Orientada a Objetos
- Spring Boot
- API REST
- Métodos HTTP
- CRUD
- Arquitetura em camadas
- Spring Data JPA
- Hibernate
- PostgreSQL
- Validação de dados
- Tratamento de exceções

## Autor

**Desenvolvimento:** Gustavo Mautoni

**Apresentação acadêmica:** Gustavo Mautoni e Bryan Paz

GitHub: [gumautoni](https://github.com/gumautoni)

Projeto desenvolvido para fins acadêmicos.