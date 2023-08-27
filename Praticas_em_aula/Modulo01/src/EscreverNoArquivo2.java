import java.io.*;
import java.util.Scanner;

public class EscreverNoArquivo2 {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        Para pegar as letras do teclado e escrever dentro de um arquivo de texto,
        podemos usar o PrintStream junto com um Scanner. O Scanner servirá como um
        leitor de buffer e conversor, permitindo reduzir a quantidade de código necessário.

        No entanto, uma ressalva é que o uso dessa abordagem sobrescreverá o conteúdo
        do arquivo com o novo texto digitado no teclado. Ele não apenas adiciona o novo
        conteúdo ao arquivo, mas substitui o conteúdo existente.
        */

        // Cria um Scanner para ler a entrada do teclado
        Scanner entrada = new Scanner(System.in);

        // Cria um objeto PrintStream para escrever no arquivo "arquivo.txt"
        PrintStream ps = new PrintStream("arquivo.txt");

        // Loop para ler cada linha digitada e escrever no arquivo
        while (entrada.hasNextLine()) {
            ps.println(entrada.nextLine()); // Escreve a linha lida no arquivo
        }

        entrada.close(); // Fecha o Scanner após a leitura
        ps.close(); // Fecha o PrintStream após a escrita
    }
}
