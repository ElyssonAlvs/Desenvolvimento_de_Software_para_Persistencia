import java.io.*;

public class EscreveNoFinal {
    public static void main(String[] args) throws IOException {
        // Escrever em um arquivo no modo append (acrescentar ao final)
        OutputStream os = new FileOutputStream("arquivo.txt", true);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        // Escrever de fato
        bw.write("celular"); // Escreve a string "celular" no arquivo
        bw.newLine(); // Escreve uma nova linha
        bw.close(); // Fecha o BufferedWriter após a escrita
    }
}
