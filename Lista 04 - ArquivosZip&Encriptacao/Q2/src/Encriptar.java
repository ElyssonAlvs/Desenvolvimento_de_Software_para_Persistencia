/*
Crie uma aplicação em Java que recebe via linha de comando :
    (1) o nome de um arquivo a ser encriptado
    (2) o nome do arquivo encriptado a ser criado
    (3) a chave de encriptação.
 */
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Encriptar {
    public static void main(String[] args) throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Verifica se foram fornecidos exatamente três argumentos na linha de comando
        if(args.length != 3){
            System.out.println("Uso : java src/Encriptar.java <arquivo_origem> <arquivo_destino> <chave> ");
            return; // Encerra o programa se os argumentos estiverem incorretos
        }

        // Obtém o nome do arquivo de origem, o nome do arquivo de destino e a chave de encriptação dos argumentos
        String sourceFile = args[0];
        String targetFile = args[1];
        String key = args[2];

        try{
            // Ajusta a chave para ter o tamanho correto (128 bits)
            byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 16);
            // Carrega a chave ajustada e inicializa o objeto de cifra
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // Configura a cifra para o modo de encriptação
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Lê o arquivo de origem e armazena seus bytes em bytesSourceFile
            byte[] bytesSourceFile = readFile(sourceFile);

            // Encripta os dados lidos do arquivo de origem
            byte[] bytesEncrypted = cipher.doFinal(bytesSourceFile);

            // Escreve os dados encriptados no arquivo de destino
            writeFile(targetFile, bytesEncrypted);

            System.out.println("Encriptacao concluida");
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            // Em caso de exceção (por exemplo, erro de E/S ou algoritmo de cifra inválido), imprime uma mensagem de erro
            System.out.println("Erro ao encriptar : " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Ler arquivos para encriptar
    public static byte[] readFile(String nameFile) throws IOException {
        // Abre um fluxo de entrada de arquivo usando o nome do arquivo especificado
        try (FileInputStream fis = new FileInputStream(nameFile)) {
            // Cria um buffer para armazenar os dados lidos do arquivo
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // Cria um buffer de bytes com tamanho 4096 para ler o arquivo em blocos
            byte[] buffer = new byte[4096];
            int bytesRead;
            // Entra em um loop que lê o arquivo em blocos até o final do arquivo
            while ((bytesRead = fis.read(buffer)) != -1) {
                // Escreve os bytes lidos no buffer de saída (ByteArrayOutputStream)
                bos.write(buffer, 0, bytesRead);
            }
            // Retorna o conteúdo lido do arquivo como um array de bytes
            return bos.toByteArray();
        }
    }

    public static void writeFile(String nameFile, byte[] content) throws IOException {
        // Abre um fluxo de saída de arquivo usando o nome do arquivo especificado
        try (FileOutputStream fos = new FileOutputStream(nameFile)) {
            // Escreve o conteúdo (um array de bytes) no arquivo usando o fluxo de saída
            fos.write(content);
        }
    }

}