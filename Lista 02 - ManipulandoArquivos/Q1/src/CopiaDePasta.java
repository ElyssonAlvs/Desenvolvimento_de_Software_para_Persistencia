import java.io.*;

/*
   Para executar o programa, no terminal, escreva os seguintes comandos:

   java src/CopiaDePasta.java <nome_de_arquivo_de_origem.txt> <nome_de_arquivo_de_destino.txt>

*/

public class CopiaDePasta {
    public static void main(String[] args) throws IOException {
        // Obtém os nomes dos arquivos de origem e destino dos argumentos
        String arquivoOrigem = args[0];
        String arquivoDestino = args[1];

        try (
                // Abre o arquivo de origem em modo de leitura e o arquivo de destino em modo de escrita
                InputStream is = new FileInputStream(arquivoOrigem);
                OutputStream os = new FileOutputStream(arquivoDestino);
        ) {
            // Marca o tempo de início da cópia
            long startTime = System.currentTimeMillis();

            int byteLido;
            // Loop para ler byte a byte do arquivo de origem e escrever no arquivo de destino
            while ((byteLido = is.read()) != -1) {
                os.write(byteLido);
            }

            // Marca o tempo de término da cópia
            long endTime = System.currentTimeMillis();
            // Calcula o tempo total da cópia em milissegundos
            long totalTime = endTime - startTime;

            // Imprime a mensagem de conclusão da cópia e o tempo total
            System.out.println("Copia concluida em " + totalTime + " ms.");
        } catch (IOException e) {
            // Em caso de erro de E/S, imprime a mensagem de erro
            System.err.println("Ocorreu um erro de E/S: " + e.getMessage());
            // exibirá informações detalhadas sobre a exceção no console
            e.printStackTrace();
        }
    }
}
