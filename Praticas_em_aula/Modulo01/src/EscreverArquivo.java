import java.io.*;
import java.util.Scanner;

public class EscreverArquivo {
    public static void main(String[] args) throws IOException {
        // Pegar o arquivo de texto
        InputStream is = new FileInputStream("arquivo.txt");

        // Pegar um fluxo de entrada e criar um Scanner para ler o arquivo
        Scanner entrada = new Scanner(is);

        // Loop para ler e imprimir cada linha do arquivo
        while (entrada.hasNextLine()) {
            System.out.println(entrada.nextLine()); // Imprime a próxima linha do arquivo
        }

        entrada.close(); // Fecha o Scanner após a leitura do arquivo
    }
}
