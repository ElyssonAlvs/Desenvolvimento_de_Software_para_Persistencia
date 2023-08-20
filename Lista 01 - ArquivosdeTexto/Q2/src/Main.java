import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Pedir o usuário o nome do arquivo e a palavra busca para encontrar a linha e mostrar ao usuario
        System.out.println("Digite o nome do arquivo e a palavra, será mostrada a linha que essa palavra pertence : ");

        // Cria um Scanner para ler entradas do usuário a partir da linha de comando.
        Scanner palavra = new Scanner(System.in);
        
        // Lê o nome do arquivo a ser lido a partir da entrada do usuário.
        String nomeArquivo = palavra.nextLine();
        
        // Lê a string de busca a partir da entrada do usuário.
        String procura = palavra.nextLine();

        // Abre um fluxo de entrada para o arquivo especificado pelo usuário.
        InputStream arquivo = new FileInputStream(nomeArquivo);
        
        // Cria um InputStreamReader para converter bytes em caracteres.
        InputStreamReader leitor = new InputStreamReader(arquivo);
        
        // Cria um BufferedReader para ler o arquivo de forma eficiente, armazenando o conteúdo em buffer.
        BufferedReader br = new BufferedReader(leitor);

        // Lê a primeira linha do arquivo.
        String linha = br.readLine();
        
        // Enquanto ainda houver linhas no arquivo:
        while (linha != null) {
            // Verifica se a linha contém a string de busca.
            if (linha.contains(procura)) {
                // Se contiver a string, imprime a linha no console.
                System.out.println(linha);
            }
            // Lê a próxima linha.
            linha = br.readLine();
        }
        
        // Fecha os recursos abertos (arquivo e leitor) para evitar vazamentos.
        br.close();
        leitor.close();
    }
}
