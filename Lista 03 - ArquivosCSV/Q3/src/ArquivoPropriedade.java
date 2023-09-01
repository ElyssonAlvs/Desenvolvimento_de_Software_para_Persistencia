/*
Escreva um arquivo de propriedades Java via editor de textos.
Esse arquivo deve ter os dados de chave e valor. Exemplo:

arquivo config.properties
arquivo = meu_arquivo.txt
linha_inicial = 1
linha_final = 3
Depois, escreva uma classe Java que exibe da linha_inicial até a linha_final do arquivo,
conforme definidos no arquivo de propriedades config.properties.
 */

/*
Exemplo de execução no terminal :

java src/ArquivoPropriedade.java config.properties
 */

import java.io.*;
import java.util.Properties;

public class ArquivoPropriedade {
    public static void main(String[] args) {
        try {
            // Carregar as configurações do arquivo config.properties
            Properties config = new Properties();
            FileReader configFile = new FileReader("config.properties");
            config.load(configFile);

            // Extrair os valores das configurações
            String fileName = config.getProperty("arquivo");
            int firstLine = Integer.parseInt(config.getProperty("linha_inicial"));
            int lastLine = Integer.parseInt(config.getProperty("linha_final"));

            // Ler o arquivo especificado nas configurações
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Pular as linhas iniciais e imprimir as linhas até o limite
            reader.lines()
                    .skip(firstLine - 1)
                    .limit(lastLine - firstLine + 1)
                    .forEach(System.out::println);

            // Fechar o arquivo de configuração e o leitor
            configFile.close();
            reader.close();

        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
    }
}
