import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MostrarLetra {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Por favor, forneça o nome do arquivo como argumento.");
            return;
        }
        // Quando for executar : java EscreverLetra nome-do-arquivo.txt
        // Uma forma de "pegar" o arquivo que se deseja usar
        InputStream is = new FileInputStream(args[0]);
        int b = is.read(); // variável que vai ler a primeira linha do arquivo, no caso vai apresentar um int
        System.out.println(b); // escrever a variável de leitura

        is.close(); // Feche o InputStream após o uso
    }
}
