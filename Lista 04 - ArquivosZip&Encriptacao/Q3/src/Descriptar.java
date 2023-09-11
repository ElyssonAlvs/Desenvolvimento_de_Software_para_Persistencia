/*
Crie uma aplicação em Java que recebe via linha de comando:

    (1) o nome de um arquivo a ser decriptado
    (2) o nome do arquivo resultante da decriptação
    (3) a chave de decriptação.
 */

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Descriptar {
    public static void main(String[] args) throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        // Verifica se foram fornecidos exatamente três argumentos na linha de comando
        if (args.length != 3) {
            System.out.println("Uso : java src/Descriptografar.java <arquivo_criptografado> <arquivo_resultante> <chave> ");
            return; // Encerra o programa se os argumentos estiverem incorretos
        }

        // Obtém o nome do arquivo criptografado, o nome do arquivo resultante e a chave de descriptografia dos argumentos
        String encryptedFile = args[0];
        String resultFile = args[1];
        String key = args[2];

        try {
            // Ajusta a chave para ter o tamanho correto (128 bits)
            byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 16);
            // Carrega a chave ajustada e inicializa o objeto de cifra
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // Configura a cifra para o modo de descriptografia
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Lê o arquivo criptografado e armazena seus bytes em bytesEncryptedFile
            byte[] bytesEncryptedFile = readFile(encryptedFile);

            // Descriptografa os dados lidos do arquivo criptografado
            byte[] bytesDecrypted = cipher.doFinal(bytesEncryptedFile);

            // Escreve os dados descriptografados no arquivo resultante
            writeFile(resultFile, bytesDecrypted);

            System.out.println("Descriptografia concluida");
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            // Em caso de exceção (por exemplo, erro de E/S ou algoritmo de cifra inválido), imprime uma mensagem de erro
            System.out.println("Erro ao descriptografar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static byte[] readFile(String filename) throws IOException{
        // Abre um fluxo de entrada de arquvios usando o nome do arquivo especificado
        try(FileInputStream fis = new FileInputStream(filename)){
            // Cria um buffer para armazenar os dados lidos do arquivo
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // Cria um buffer de bytes com tamannho 4096 para ler o arquivo em blocos
            byte[] buffer = new byte[4096];
            int byteRead;
            // Entra em um loop que lê o arquivo em blocos até o final do arquivo
            while((byteRead = fis.read(buffer)) != -1){
                // Escreve os bytes lidos no buffe de saída (ByteOutputStream)
                bos.write(buffer, 0, byteRead);
            }
            // Retorna o conteúdo lido do arquivo como um arry de bytes
            return bos.toByteArray();
        }catch (IOException e){
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
        return new byte[0];
    }

    // Função para escrever arquivos
    public static void writeFile(String fileName, byte[] content) throws IOException {
        // Abre um fluxo de saída de arquivo usando o nome do arquivo especificado
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            // Escreve o conteúdo (um array de bytes) no arquivo usando o fluxo de saída
            fos.write(content);
        }
    }
}