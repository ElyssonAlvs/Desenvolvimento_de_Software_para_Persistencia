import java.io.*;
import java.util.Scanner;

public class VerificadorLinhas {
    public static void main(String[] args) throws IOException {
        // Pegar o arquivo a ser usado
        InputStream is = new FileInputStream("arquivo.txt");

        // Fluxo de entrada do teclado
        Scanner entrada = new Scanner(is);

        // Loop para verificar e imprimir se há mais linhas no arquivo
        while (entrada.hasNextLine()) {
            System.out.println(entrada.hasNextLine()); // Isso imprime se há mais linhas no arquivo, um bool
            break; // Sai do loop após a primeira iteração
        }

        is.close(); // Fecha o fluxo de entrada após o uso
    }
}
