# Lista 06 - JDBC e DAO

**Módulo 2: Desenvolvimento para Persistência 
usando Bancos Relacionais com Java JDBC e Padrão 
de Projeto DAO.**

1. Crie uma aplicação que use um banco relacional contendo uma tabela de produtos, com sua respectiva Classe (Entidade) Java. Cada produto deve ter um id, código, descrição, preço, quantidade em estoque e data da última entrada.
2. Crie uma implementação de DAO usando JDBC que representa uma interface única para persistência dos dados. Crie e use no DAO somente os métodos realmente necessários para a resolução desta lista.r
3. Crie métodos no DAO para inserir, deletar, alterar e consultar produtos. As seguintes consultas devem ser realizadas pela aplicação:
   - Obter o produto por id.
   - Obter o produto por código.
   - Obter os produtos por descrição. Parte da string de consulta (substring) é usada para obtenção dos produtos.
   - Dado um preço, obter os produtos com valores menores ou iguais a esse preço.
   - Dadas uma data inicial e uma data final, obter os produtos cuja data da última entrada está entre essas datas.
4. A aplicação deve ter um menu para realizar as funcionalidades previstas nas questões anteriores (consultas e atualizações).