import java.io.*;

public class EscreverNoArquivo3 {
    public static void main(String[] args) {
        try (
                OutputStream os = new FileOutputStream("arquivo.txt", true); // Abre o arquivo no modo append
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(os)) // Cria um PrintWriter para escrever no arquivo
        ) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Cria um leitor de buffer para entrada do teclado
            System.out.println("Digite o texto a ser adicionado no arquivo (ou 'sair' para encerrar):");

            String input;
            while (!(input = br.readLine()).equalsIgnoreCase("sair")) { // Loop para ler texto do teclado
                pw.println(input); // Escreve o texto no arquivo
            }
        } catch (IOException e) { // Captura exceções de E/S
            System.err.println("Ocorreu um erro de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
