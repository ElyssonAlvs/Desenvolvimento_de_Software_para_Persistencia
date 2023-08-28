import java.io.*;

public class Conversao {
    public static void main(String[] args) throws IOException {
        // Obtém os nomes dos arquivos de origem e destino dos argumentos
        String arquivoOrigem = args[0];
        String arquivoDestino = args[1];

        try (
                // Abre o arquivo de origem em modo de leitura com codificação ISO-8859-1
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoOrigem),"ISO-8859-1"));
                // Abre o arquivo de destino em modo de escrita com codificação UTF-8
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoDestino), "UTF-8"));
        ) {
            String linha;
            // Loop para ler cada linha do arquivo de origem
            while((linha = br.readLine()) != null){
                // Escreve a linha no arquivo de destino e adiciona uma nova linha
                bw.write(linha);
                bw.newLine();
            }

            System.out.println("Conversão concluída.");
        } catch (IOException e){
            // Em caso de erro de E/S, imprime a mensagem de erro
            System.out.println("Ocorreu um erro de E/S " + e);
            e.printStackTrace();
        }
    }
}
