import java.io.*;

// Um papagaio
public class Papagaio {
    public static void main(String[] args) throws IOException {
        // Cria uma sequência de entrada padrão (console)
        InputStream is = System.in;
        // Converte os bytes da sequência de entrada em caracteres
        InputStreamReader isr = new InputStreamReader(is);
        // Cria um buffer de leitura para ler linhas de caracteres
        BufferedReader br = new BufferedReader(isr);
        // Lê a primeira linha da sequência de entrada
        String s = br.readLine();

        // Loop para repetir a leitura e impressão de linhas enquanto não for nulo
        while (s != null) {
            // Imprime a linha lida
            System.out.println(s);
            // Lê a próxima linha da sequência de entrada
            s = br.readLine();
        }

        // O loop termina quando uma linha nula é encontrada (geralmente quando Ctrl + D é pressionado)
        // Fecha os recursos após a conclusão
        br.close();
        isr.close();
        is.close();
    }
}
