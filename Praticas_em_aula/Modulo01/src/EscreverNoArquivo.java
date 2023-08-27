import java.io.*;

public class EscreverNoArquivo {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        Outra maneira de escrever um arquivo é usando o PrintStream.
        A diferença principal é que o PrintStream sobrescreve o arquivo,
        independentemente do que já está escrito.
        */

        // Cria um objeto PrintStream para escrever no arquivo "arquivo.txt"
        PrintStream ps = new PrintStream("arquivo.txt");

        // Escreve uma linha no arquivo usando println()
        ps.println("Teste com PrintStream");

        // Fecha o PrintStream após a escrita
        ps.close();
    }
}
