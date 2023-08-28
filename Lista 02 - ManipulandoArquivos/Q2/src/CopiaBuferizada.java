import java.io.*;

public class CopiaBuferizada {
    public static void main(String[] args) {
        // Obtém os nomes dos arquivos de origem e destino dos argumentos
        String arquivoOrigem = args[0];
        String arquivoDestino = args[1];

        try (
                // Abre o arquivo de origem em modo de leitura e o arquivo de destino em modo de escrita
                InputStream is = new FileInputStream(arquivoOrigem);
                OutputStream os = new FileOutputStream(arquivoDestino);
        ) {
            byte[] buffer = new byte[8192]; // Buffer de 8192 bytes

            // Marca o tempo de início da cópia
            long startTime = System.currentTimeMillis();

            int bytesRead;
            // Loop para ler blocos de bytes do arquivo de origem e escrever no arquivo de destino
            while ((bytesRead = is.read(buffer)) != -1) {
                // o array de bytes que será escrito, de onde vai começar, no caso de 0, e por fim o número de bytes que
                // se deseja escrever no fluxo de saída
                os.write(buffer, 0, bytesRead);
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
            e.printStackTrace();
        }
    }
}
