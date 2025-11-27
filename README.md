# Sistema de Controle de Estoque

Sistema web desenvolvido em Java Servlet e JSP para gerenciamento de estoque de produtos.

## 📋 Descrição

Sistema completo de controle de estoque que permite gerenciar produtos, categorias, movimentações (entrada/saída) e gerar diversos relatórios para auxiliar na gestão do estoque.

## 🚀 Tecnologias Utilizadas

- **Java 11**
- **Java Servlet API**
- **Java Server Pages (JSP)**
- **JSTL (JavaServer Pages Standard Tag Library)**
- **H2 Database** (banco de dados em memória)
- **HTML5 / CSS3**
- **JavaScript**
- **Maven** (gerenciamento de dependências)
- **Docker** (containerização)
- **Tomcat 9.0** (servidor de aplicação)

## ✨ Funcionalidades

### Gestão de Produtos
- ✅ Cadastro, edição, consulta e exclusão de produtos
- ✅ Informações do produto: nome, preço unitário, unidade, quantidade em estoque, quantidade mínima/máxima, categoria

### Gestão de Categorias
- ✅ Cadastro, edição, consulta e exclusão de categorias
- ✅ Informações da categoria: nome, tamanho (Pequeno, Médio, Grande), embalagem (Lata, Vidro, Plástico)

### Movimentações de Estoque
- ✅ Registro de entradas e saídas de produtos
- ✅ Atualização automática do saldo do estoque
- ✅ Alertas quando quantidade está abaixo do mínimo (saída) ou acima do máximo (entrada)

### Reajuste de Preços
- ✅ Reajuste percentual de preços de todos os produtos de uma vez

### Relatórios
1. **Lista de Preços**: Todos os produtos em ordem alfabética com preços, unidade e categoria
2. **Balanço Físico/Financeiro**: Quantidade disponível, valor unitário, valor total por produto e valor total do estoque
3. **Produtos Abaixo do Mínimo**: Lista de produtos que precisam de reposição
4. **Produtos por Categoria**: Quantidade de produtos distintos por categoria
5. **Produtos Mais Movimentados**: Produto com mais saída e produto com mais entrada

## 📦 Estrutura do Projeto

```
sistema-estoque/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/estoque/
│   │   │       ├── model/
│   │   │       │   ├── Produto.java
│   │   │       │   ├── Categoria.java
│   │   │       │   └── Movimentacao.java
│   │   │       ├── dao/
│   │   │       │   └── EstoqueDAO.java
│   │   │       └── servlet/
│   │   │           ├── ProdutoServlet.java
│   │   │           ├── CategoriaServlet.java
│   │   │           ├── MovimentacaoServlet.java
│   │   │           ├── ReajustePrecoServlet.java
│   │   │           └── RelatorioServlet.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── css/
│   │       │   └── style.css
│   │       ├── index.jsp
│   │       ├── produto-list.jsp
│   │       ├── produto-form.jsp
│   │       ├── categoria-list.jsp
│   │       ├── categoria-form.jsp
│   │       ├── movimentacao-form.jsp
│   │       ├── reajuste-preco.jsp
│   │       ├── relatorios.jsp
│   │       ├── relatorio-lista-precos.jsp
│   │       ├── relatorio-balanco.jsp
│   │       ├── relatorio-abaixo-minimo.jsp
│   │       ├── relatorio-por-categoria.jsp
│   │       └── relatorio-mais-movimentado.jsp
│   └── pom.xml
└── README.md
```

## 🛠️ Como Executar

### Opção 1: Usando Docker (Recomendado) 🐳

#### Pré-requisitos
- Docker instalado
- Docker Compose instalado

#### Passos para Execução

1. **Clone ou baixe o projeto**

2. **Construir e executar com Docker Compose**
   ```bash
   docker-compose up --build
   ```

3. **Acesse a aplicação**
   - Abra o navegador em: `http://localhost:8080/`

4. **Parar a aplicação**
   ```bash
   docker-compose down
   ```

#### Comandos úteis do Docker

- **Ver logs**: `docker-compose logs -f`
- **Reconstruir**: `docker-compose up --build`
- **Executar em background**: `docker-compose up -d`

#### Opção com Banco H2 Persistente (Opcional)

Se você quiser usar um banco de dados H2 persistente (os dados não serão perdidos ao reiniciar):

```bash
docker-compose -f docker-compose.h2.yml up --build
```

Isso iniciará:
- A aplicação na porta `8080`
- Console web do H2 na porta `8082` (acesse: `http://localhost:8082`)

**Nota**: Para usar o H2 externo, você precisará modificar a URL no arquivo `EstoqueDAO.java`:
```java
private static final String URL = "jdbc:h2:tcp://h2-database:9092/./estoque";
```

### Opção 2: Execução Manual

#### Pré-requisitos
- Java JDK 11 ou superior
- Maven 3.6 ou superior
- Servidor de aplicação (Tomcat 9.0 ou superior)

#### Passos para Execução

1. **Clone ou baixe o projeto**

2. **Compile o projeto**
   ```bash
   mvn clean compile
   ```

3. **Empacote o projeto (gera o arquivo WAR)**
   ```bash
   mvn clean package
   ```

4. **Deploy no Tomcat**
   - Copie o arquivo `target/sistema-estoque-1.0.0.war` para a pasta `webapps` do Tomcat
   - Inicie o servidor Tomcat
   - Acesse: `http://localhost:8080/sistema-estoque-1.0.0/`

   Ou se renomear o WAR para `ROOT.war`:
   - Acesse: `http://localhost:8080/`

### Executar com Maven Tomcat Plugin (Alternativa)

Você também pode usar o plugin do Tomcat diretamente:

```bash
mvn clean tomcat7:run
```

## 📝 Observações Importantes

- O banco de dados H2 é em memória, então os dados serão perdidos ao reiniciar o servidor
- Para persistência permanente, altere a URL do banco no arquivo `EstoqueDAO.java`:
  ```java
  private static final String URL = "jdbc:h2:file:./estoque;DB_CLOSE_DELAY=-1";
  ```

## 🎨 Interface

A interface foi desenvolvida com:
- Design moderno e responsivo
- Cores gradientes e layout limpo
- Navegação intuitiva
- Validações em JavaScript
- Alertas visuais para situações importantes

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.

## 👨‍💻 Autor

Desenvolvido como projeto acadêmico de sistema web utilizando Java Servlet e JSP.

