## Lista 03 - Manipulando CSV e Arquivos de Propriedade

Importante:

- Resolva cada questão em um arquivo separado.
- Sempre coloque o enunciado completo da questão em cada arquivo que contém sua resolução. Se a questão for um código-fonte, colocar o enunciado como comentário.
- Enviar as resoluções das questões compactadas em um arquivo em formato zip.

1.Crie uma aplicação em Java que recebe via linha de comando:
- (1) o nome de um arquivo CSV; 
- (2) o delimitador usado para separar os campos do arquivo; 
- (3) uma lista de nomes das colunas do arquivo CSV que serão processados. 
- Considere que o arquivo CSV : 
  - (1) deva ter um cabeçalho com os nomes das colunas em sua primeira linha e que não tenha internamente colunas com 
  Strings contendo o mesmo caractere usado como delimitador 
  - (2). A aplicação deve exibir a soma e a média das colunas selecionadas em 
  - (3), caso tenham dados numéricos. Se não tiverem dados numéricos, somente exibir que aquela coluna não é numérica. 
- Não usar bibliotecas externas para resolver esta questão (usar Java puro). 
Sugere-se navegar apenas uma única vez em cada linha do arquivo CSV. 
Fazer a aplicação de modo que ela possa processar arquivos CSV extremamente grandes, mesmo que não caibam na memória RAM.

**Dica: usar o método split da classe String para separar os valores das colunas em cada linha do arquivo CSV.**

2. Escolha e baixe um arquivo csv a partir do link e use-o nas questões a seguir. Na resolução desta questão, não enviar os 
arquivos envolvidos, mas somente os resultados pedidos em arquivo texto com extensão txt. Envie somente o link do arquivo 
CSV escolhido.
- Gere as somas checksum, md5, sha1 e sha256 do arquivo csv escolhido, usando utilitários de linha de comando do próprio sistema operacional ou instalado nele.
Compare os tempos de execução e tamanhos dos arquivos gerados. 
**Dica: para obter o tempo de execução, use o comando time.**
  - Exemplo: time md5sum iris.csv

3. Escreva um arquivo de propriedades Java via editor de textos. Esse arquivo deve ter os dados de chave e valor. Exemplo:
- arquivo config.properties
  - arquivo = meu_arquivo.txt
  - linha_inicial = 1
  - linha_final = 3
- Depois, escreva uma classe Java que exibe da linha_inicial até a linha_final do arquivo, 
conforme definidos no arquivo de propriedades config.properties.