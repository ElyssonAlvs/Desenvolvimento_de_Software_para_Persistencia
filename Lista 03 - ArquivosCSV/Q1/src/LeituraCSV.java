/*
Crie uma aplicação em Java que recebe via linha de comando:
(1) o nome de um arquivo CSV;
(2) o delimitador usado para separar os campos do arquivo;
(3) uma lista de nomes das colunas do arquivo CSV que serão processados.
Considere que o arquivo CSV :
    (1) deva ter um cabeçalho com os nomes das colunas em sua primeira linha e que não tenha internamente
        colunas com Strings contendo o mesmo caractere usado como delimitador
    (2). A aplicação deve exibir a soma e a média das colunas selecionadas em (3), caso tenham dados numéricos.
    Se não tiverem dados numéricos, somente exibir que aquela coluna não é numérica.
    Não usar bibliotecas externas para resolver esta questão (usar Java puro).
    Sugere-se navegar apenas uma única vez em cada linha do arquivo CSV.
    Fazer a aplicação de modo que ela possa processar arquivos CSV extremamente grandes, mesmo que não caibam na memória RAM.
    Dica: usar o método split da classe String para separar os valores das colunas em cada linha do arquivo CSV.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
Exemplo de execução no terminal :

java src/LeituraCSV.java arquivo.csv "," valor1 valor2
 */
public class LeituraCSV {
    public static void main(String[] args) {

        final String csvFile = args[0];
        final String delimiter = args[1];

        // Obter a lista de colunas a serem processadas dos argumentos
        ArrayList<String> usedColumns = argsSplit(args);

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            // Ler a primeira linha do arquivo para obter os nomes das colunas
            List<String> allColumns = Arrays.asList(reader.readLine().split(delimiter));

            // Ler as linhas restantes do arquivo e armazená-las em uma lista de listas de strings
            ArrayList<List<String>> lines = reader.lines()
                    .map(line -> Arrays.stream(line.split(delimiter)).toList())
                    .collect(Collectors.toCollection(ArrayList::new));

            // Para cada coluna a ser processada
            for (String value : usedColumns) {

                try {
                    // Obter o índice da coluna no cabeçalho
                    int index = allColumns.indexOf(value);

                    // Calcular a soma dos valores na coluna
                    double sum = 0;
                    for (var line : lines) {
                        sum += Double.parseDouble(line.get(index));
                    }

                    // Exibir a soma e a média dos valores na coluna
                    System.out.printf("%s: \tSoma: %f\n\t\tMedia: %f\n", value, sum, sum / lines.size());
                } catch (NumberFormatException e) {
                    // Lidar com colunas não numéricas ou nulas
                    System.out.println("\n" + value + ":\tColuna com valores não-numéricos ou nulos...\n");
                }
            }
        } catch (IOException e) {
            // Lidar com exceções de E/S
            System.out.println(e.getMessage());
        }
    }

    // Função para obter a lista de colunas dos argumentos
    static ArrayList<String> argsSplit(String[] args) {
        ArrayList<String> argsList = new ArrayList<>();

        // Iterar pelos argumentos a partir do índice 2 (ignorando o nome do arquivo e o delimitador)
        IntStream.range(2, args.length)
                .forEach(value -> argsList.add(args[value]));

        return argsList;
    }
}

