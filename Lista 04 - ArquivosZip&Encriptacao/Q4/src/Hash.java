/*
Crie uma aplicação em Java que recebe via linha de comando :
    (1) o nome de um arquivo para geração/armazenamento dos hashes  md5, sha1 e sha256 do arquivo especificado.
    A aplicação deve mostrar o tempo de execução de cada uma dessas operações
 */
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {
    public static void main(String[] args) throws IOException {
        // Verificar os argumentos da linha de comando
        if (args.length != 1){
            System.out.println("Use : java src/Hashe.java <nome_do_arquivo>");
            return;
        }
        // Obter o nome do arquivo via linha de comando
        String fileName = args[0];
        // Verificar se ele existe
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("Arquivo nao encontrado : " + fileName);
            return;
        }
        // Gerar o hash MD5 medindo o tempo de execução
        long startTimeMD5 = System.currentTimeMillis();
        String md5Hash = generateHash(file, "MD5");
        long endTimeMD5 = System.currentTimeMillis();

        // Gera o hash SHA-1 e mede o tempo de execução
        long startTimeSHA1 = System.currentTimeMillis();
        String sha1Hash = generateHash(file, "SHA-1");
        long endTimeSHA1 = System.currentTimeMillis();

        // Gera o hash SHA-256 e mede o tempo de execução
        long startTimeSHA256 = System.currentTimeMillis();
        String sha256Hash = generateHash(file, "SHA-256");
        long endTimeSHA256 = System.currentTimeMillis();

        // Exibe os hashes e os tempos de execução
        System.out.println("MD5 Hash: " + md5Hash);
        System.out.println("Tempo de execucao (MD5): " + (endTimeMD5 - startTimeMD5) + " ms");

        System.out.println("SHA-1 Hash: " + sha1Hash);
        System.out.println("Tempo de execucao (SHA-1): " + (endTimeSHA1 - startTimeSHA1) + " ms");

        System.out.println("SHA-256 Hash: " + sha256Hash);
        System.out.println("Tempo de execucao (SHA-256): " + (endTimeSHA256 - startTimeSHA256) + " ms");
    }

    // Função para gerar os hash
    // Método para gerar um hash de um arquivo usando um algoritmo específico
    public static String generateHash(File file, String algorithm) {
        try {
            // Cria uma instância do MessageDigest com o algoritmo desejado (por exemplo, "MD5")
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // Abre um fluxo de entrada de arquivo usando o nome do arquivo especificado
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Lê o arquivo em blocos e atualiza o objeto MessageDigest com os dados lidos
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            // Finaliza o cálculo do hash e obtém os bytes do hash resultante
            byte[] hashBytes = digest.digest();

            // Converte os bytes do hash em uma representação hexadecimal
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }

            // Fecha o fluxo de entrada de arquivo
            fis.close();

            // Retorna o hash em formato hexadecimal como uma string
            return hexHash.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            // Em caso de exceção (por exemplo, algoritmo de hash inválido ou erro de E/S), imprime uma mensagem de erro
            e.printStackTrace();
            return null; // Retornar null em caso de erro
        }
    }

}