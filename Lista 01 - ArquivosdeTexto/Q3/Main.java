import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Solicita ao usuário que insira os nomes dos arquivos separados por linhas.
        System.out.println("Nome dos arquivos, separados por 'Enter': ");
        Scanner leitor = new Scanner(System.in);
        
        // Lê os nomes dos arquivos da entrada do usuário.
        String arquivo1 = leitor.nextLine();
        String arquivo2 = leitor.nextLine();

        // Abre fluxos de entrada para os arquivos especificados pelo usuário.
        InputStream arq1 = new FileInputStream(arquivo1);
        InputStreamReader read1 = new InputStreamReader(arq1);
        BufferedReader br1 = new BufferedReader(read1);

        InputStream arq2 = new FileInputStream(arquivo2);
        InputStreamReader read2 = new InputStreamReader(arq2);
        BufferedReader br2 = new BufferedReader(read2);

        // Lê e imprime as linhas do primeiro arquivo.
        String linha = br1.readLine();
        System.out.println("\n Arquivo 1: \n");
        while (linha != null) {
            System.out.println(linha);
            linha = br1.readLine();
        }

        // Lê e imprime as linhas do segundo arquivo.
        linha = br2.readLine();
        System.out.println("\n Arquivo 2: \n");
        while (linha != null) {
            System.out.println(linha);
            linha = br2.readLine();
        }

        // Fecha os recursos abertos para evitar vazamentos de memória.
        br1.close();
        br2.close();
    }
}

