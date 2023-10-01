import java.io.File;
import java.io.*;

public class ArquivosDiretorio {
    public static void main(String[] args) throws IOException {
        // Define os caminhos dos arquivos e diretórios
        File arquivo = new File("C:/Users/elyss/OneDrive/Área de Trabalho/Persistência/Udemy - Java/Java-Basico/src/conteudos.txt");
        File diretorio = new File("C:/Users/elyss/OneDrive/Área de Trabalho/Persistência/Udemy - Java/Java-Basico/src/arquivos");

        // Define o caminho do novo arquivo após a renomeação
        File nomeArquivoNovo = new File("C:/Users/elyss/OneDrive/Área de Trabalho/Persistência/Udemy - Java/Java-Basico/src/conteudos-extra.txt");

        // Cria um arquivo
        criarArquivo(arquivo);

        // Lista os arquivos em um diretório
        ListarArquivos(diretorio);

        // Renomeia o arquivo
        boolean renomeado = arquivo.renameTo(nomeArquivoNovo);

        // Verifica se o arquivo foi renomeado com sucesso
        if (renomeado) {
            System.out.println("Arquivo renomeado com sucesso");
        } else {
            System.out.println("Falha ao renomear arquivo !");
        }
    }

    // Função para listar os arquivos em um diretório
    private static void ListarArquivos(File diretorio) {
        if (diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arq : arquivos) {
                    System.out.println("O nome do arquivo é : " + arq.getName());
                }
            }
        } else {
            System.out.println("O caminho especificado não é diretório");
        }
    }

    // Função para criar um arquivo
    private static void criarArquivo(File arquivo) {
        try {
            boolean arquivoCriado = arquivo.createNewFile();

            // Verifica se o arquivo foi criado com sucesso
            if (arquivoCriado) {
                System.out.println("Arquivo criado com sucesso!");
            } else {
                System.out.println("Falha ao criar o arquivo");
            }
        } catch (IOException e) {
            // Trata exceções de E/S (IOException) e imprime a mensagem de erro
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
