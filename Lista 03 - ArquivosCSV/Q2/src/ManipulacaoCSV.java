/*
Escolha e baixe um arquivo csv a partir do link e use-o nas questões a seguir.
Na resolução desta questão, não enviar os arquivos envolvidos, mas somente os
resultados pedidos em arquivo texto com extensão txt. Envie somente o link do arquivo CSV escolhido.

Gere as somas checksum, md5, sha1 e sha256 do arquivo csv escolhido, usando utilitários de linha de comando do próprio
sistema operacional ou instalado nele.
Compare os tempos de execução e tamanhos dos arquivos gerados.
Dica: para obter o tempo de execução, use o comando time.
Exemplo: time md5sum iris.csv

https://zenodo.org/record/3469741/files/2014_01-Traffic_Sensors_Fortaleza.csv?download=1
 */

/*
Exemplo de execução no terminal :

java src/ManipulacaoCSV.java <arquivo.csv>

 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ManipulacaoCSV {
    public static void main(String[] args) {
        // Verificação se o nome do arquivo foi fornecido como argumento
        if (args.length < 1) {
            System.out.println("Uso: java Main <nome_arquivo>");
            return;
        }

        final String fileName = args[0];
        final String fileOut = fileName.substring(0, fileName.indexOf("."));

        // Lista de algoritmos de hash a serem calculados
        List<String> sums = List.of("MD5", "SHA1", "SHA256");

        for (String sum : sums) {

            try {
                Long currentTime = System.currentTimeMillis();

                // Comando para calcular o hash usando o utilitário certutil (Windows)
                String command = "certutil -hashfile %s %s".formatted(fileName, sum);

                Process process = Runtime.getRuntime().exec(command);

                // Ler a saída do processo
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

                // Criar um escritor para escrever o resultado do hash em um arquivo
                Writer writer = new FileWriter((fileOut + sum + ".txt"));
                reader.lines()
                        .forEach(line -> {
                            try {
                                writer.append(line).append("\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });

                // Aguardar a conclusão do processo
                int exit = process.waitFor();
                if (exit == 0) {
                    System.out.println(sum + ": Concluido com sucesso\n");
                } else {
                    System.err.println("Erro ao calcular: " + sum + "\n");
                }

                Long time = System.currentTimeMillis();

                // Exibir o tempo decorrido para calcular o hash
                System.out.println("Tempo decorrido: " + (time - currentTime) + "\n");

                writer.close();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
