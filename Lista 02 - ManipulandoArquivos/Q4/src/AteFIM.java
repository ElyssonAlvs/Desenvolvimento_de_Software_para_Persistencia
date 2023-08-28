import java.io.*;

public class AteFIM {
    public static void main(String[] args) {
        try {
            // Cria um leitor de entrada para a entrada do usuário
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Inicializa um StringBuilder para armazenar o texto digitado
            StringBuilder texto = new StringBuilder();
            String linha;
            // Loop para coletar linhas de texto até que a linha "FIM" seja digitada
            while (!(linha = br.readLine()).equalsIgnoreCase("FIM")) {
                /*
                   está adicionando a linha digitada pelo usuário ao StringBuilder e 
                   incluindo uma nova linha após cada linha. Isso é feito para preservar 
                   as novas linhas originais enquanto o texto é coletado.
                 */
                texto.append(linha).append((System.lineSeparator()));
            }

            // Solicita o nome do arquivo para salvar o texto digitado
            System.out.println("Digite o nome do arquivo para onde salvar o texto digitado:");
            String nomeArquivo = br.readLine();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
                // Escreve o texto armazenado no StringBuilder no arquivo
                bw.write(texto.toString());
                System.out.println("Texto salvo com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (IOException e) {
            // Em caso de erro de E/S ao coletar entrada do usuário
            System.err.println("Erro de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
