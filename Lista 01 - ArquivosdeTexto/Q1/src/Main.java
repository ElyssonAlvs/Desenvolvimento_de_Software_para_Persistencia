import java.io.*;

public class Main {
    public static void main(String[] args) {
        // O programa espera um argumento da linha de comando, que será o caminho para o arquivo a ser lido.
        String archive = args[0];

        try (InputStream inputStream = new FileInputStream(archive)) {
            // Cria um InputStream para ler o conteúdo do arquivo.
            // O uso de try-with-resources garante que o InputStream seja fechado automaticamente após o uso.

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // Cria um InputStreamReader que converte bytes em caracteres, permitindo a leitura do arquivo de forma textual.

            BufferedReader bf = new BufferedReader(inputStreamReader);
            // Cria um BufferedReader para ler o arquivo de forma eficiente, armazenando o conteúdo em buffer.

            int i = 0; // Variável para controlar o número de linhas lidas.
            String line = bf.readLine(); // Lê a primeira linha do arquivo.

            // Enquanto ainda houver linhas no arquivo e não foram lidas mais do que 10 linhas:
            while (line != null && i < 10) {
                System.out.println(line); // Imprime a linha no console.
                line = bf.readLine(); // Lê a próxima linha.
                i++; // Incrementa o contador de linhas lidas.
            }

        } catch (IOException e) {
            // Em caso de exceção durante a leitura do arquivo:
            System.out.println(e.getMessage()); // Imprime a mensagem de erro.
        }
    }
}
