# Lista 08 - DAO e JPA Puro
1. Crie uma aplicação que use um banco relacional contendo uma tabela de produtos, com sua respectiva Classe (Entidade) Java. Cada produto deve ter um id, código, descrição, preço, quantidade em estoque e data da última entrada.

2. Crie uma implementação de DAO usando JPA Puro (Sem Spring) que representa uma interface única para persistência dos dados. Crie métodos no DAO, bem como, as respectivas interfaces de usuário (UIs) para inserir, deletar, alterar e consultar produtos, ou seja, o CRUD de produtos no DAO e na interface de usuário (UI). Além disso, as seguintes consultas devem ser realizadas pela aplicação:

- Obter o produto por id.
- Obter o produto por código.
- Obter os produtos por descrição. Parte da string de consulta (substring) é usada para obtenção dos produtos.
- Dado um preço, obter os produtos com valores menores ou iguais a esse preço.
- Dadas uma data inicial e uma data final, obter os produtos cuja data da última entrada está entre essas datas.

3. A aplicação deve ter um menu na interface com o usuário (UI) para realizar as funcionalidades previstas nas questões anteriores (consultas, inserções, remoções e atualizações).

Use uma ferramenta de Build (Maven ou Gradle) e o Lombok para reduzir bastante o código da Entidade Produto.

Você pode usar como base o projeto exemplo de JPA Puro com DAO disponível no repositório da disciplina no [GitHub da Disciplina](https://github.com/regispires/praticas_dspersist_2023/).

---

Nessa atividade utilizei o BD Relacional *PostgreSQL* portanto,
fique atento aos arquivos de configuração, o código de criação : 

```sql
SET SCHEMA 'public';
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    codigo INT,
    descricao VARCHAR(200),
    preco NUMERIC(10, 2),
    quantidade INT,
    ultima_entrada DATE
);
```