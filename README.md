# 🚀 Portfólio de Projetos Java

Este repositório documenta minha jornada de aprendizado e desenvolvimento de projetos em Java. Vindo de uma carreira consolidada em Mainframe (COBOL), meu objetivo é construir um portfólio que demonstre a aplicação de conceitos modernos de back-end, microsserviços e arquitetura de nuvem.

Este espaço também serve como repositório para projetos da pós-graduação na UTFPR.

---

## 1. Projeto: `modulo-produto` (Módulo de ERP)

O primeiro projeto deste portfólio é o `modulo-produto`, um microsserviço (ou "mini-monólito" inicial) focado no cadastro de produtos para um sistema ERP.

O objetivo deste módulo é expor uma API RESTful simples, mas completa, para realizar operações de CRUD (Create, Read, Update, Delete) de produtos, servindo como a "espinha dorsal" para futuros módulos de Estoque ou Vendas.

### 🛠️ Tecnologias e Ferramentas (Pacotes Implantados)

Este projeto foi construído e testado usando o seguinte stack:

* **Linguagem:** **Java 17 (LTS)**
* **Framework:** **Spring Boot 3+** (Usado para criar a API REST, gerenciar o servidor web embutido e facilitar a injeção de dependência).
* **Build e Dependências:** **Apache Maven 3.9+** (Usado para gerenciar todas as bibliotecas do projeto, como o Spring, JPA, etc.).
* **Acesso a Dados:** **Spring Data JPA** (Facilita a comunicação com o banco de dados, abstraindo o SQL).
* **Banco de Dados:** **H2 Database** (Um banco de dados leve, em memória, perfeito para desenvolvimento e testes rápidos).
* **Cliente de API (Testes):** **Insomnia** (Ferramenta usada para enviar requisições HTTP (`POST`, `GET`) e testar os endpoints da API).

### 🏛️ Arquitetura Inicial e Resumo do Código-Fonte

A arquitetura deste projeto segue o padrão **3-Tier (3 Camadas)**, que é fundamental no desenvolvimento com Spring Boot.



O fluxo de uma requisição (ex: criar um produto) funciona da seguinte maneira:

1.  **Camada de Controle (API) - `ProdutoController.java`**
    * **O que faz:** É a porta de entrada. Recebe as requisições HTTP do mundo externo (como o Insomnia).
    * **Anotações-chave:** `@RestController` (marca a classe como um controlador de API) e `@RequestMapping("/api/produtos")` (define a URL base).
    * **Métodos:** Usa `@PostMapping` (para criar) e `@GetMapping` (para buscar), traduzindo o JSON da requisição em objetos Java (`@RequestBody`).

2.  **Camada de Repositório (Dados) - `ProdutoRepository.java`**
    * **O que faz:** É a camada mágica que fala com o banco de dados.
    * **Como funciona:** É uma `interface` que estende `JpaRepository<Produto, Long>`.
    * **Resumo:** Ao estender `JpaRepository`, o Spring Data JPA nos dá, *automaticamente*, todos os métodos de CRUD (`save()`, `findById()`, `findAll()`, `delete()`) sem que precisemos escrever uma única linha de SQL.

3.  **Camada de Modelo (Entidade) - `Produto.java`**
    * **O que faz:** É a representação dos nossos dados. Pense nisso como o "Book" COBOL ou a DCLGEN.
    * **Anotações-chave:** `@Entity` (diz ao JPA que esta classe é uma tabela no banco) e `@Id` / `@GeneratedValue` (definem a chave primária e como ela é gerada).

**Em resumo:** O `Insomnia` envia um `POST` com um JSON -> O `ProdutoController` recebe -> Ele chama o `ProdutoRepository.save()` -> O `Repository` usa o JPA para salvar o objeto `Produto` no banco H2.

### ▶️ Como Testar (Exemplo com Insomnia)

1.  Rode a aplicação (via `Run Java` no VSCode ou `mvn spring-boot:run` no terminal).
2.  Abra o Insomnia.

#### Criar um Produto

* **Método:** `POST`
* **URL:** `http://localhost:8080/api/produtos`
* **Body (JSON):**
    ```json
    {
      "nome": "Parafuso Sextavado 10mm",
      "codigoSku": "PF-S-10",
      "precoCusto": 0.75,
      "quantidadeEstoque": 500
    }
    ```
* **Resposta (Sucesso):** Status `200 OK` com o JSON do produto criado e seu novo `id`.

#### Listar todos os Produtos

* **Método:** `GET`
* **URL:** `http://localhost:8080/api/produtos`
