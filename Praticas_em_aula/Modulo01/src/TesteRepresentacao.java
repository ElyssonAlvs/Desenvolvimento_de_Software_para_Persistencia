import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TesteRepresentacao {
    public static void main(String[] args) throws IOException {
        // Abre o arquivo especificado em modo de leitura
        InputStream is = new FileInputStream(args[0]);

        int b; // Variável para armazenar o byte lido
        int i = 1; // Variável para contar o número de bytes lidos

        // Loop para ler cada byte do arquivo
        while ((b = is.read()) != -1) {
            // Imprime o número sequencial, o valor inteiro e a representação hexadecimal do byte
            System.out.println(i++ + " " + b + " " + Integer.toHexString(b));
        }

        // Fecha o fluxo de entrada após a leitura
        is.close();
    }
}
