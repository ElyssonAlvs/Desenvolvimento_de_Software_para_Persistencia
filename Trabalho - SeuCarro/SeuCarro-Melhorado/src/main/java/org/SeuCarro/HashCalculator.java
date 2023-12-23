package org.SeuCarro;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {
    // Nome do arquivo CSV de entrada
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para calcular o hash SHA256 do arquivo CSV
    public static String calcularSHA256Hash() {
        try (FileInputStream fileInputStream = new FileInputStream(NOME_ARQUIVO_CSV)) {
            // Inicializa o objeto MessageDigest com o algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Cria um buffer para ler o arquivo em pedaços
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Lê o arquivo CSV em pedaços e atualiza o digest
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            // Calcula o hash a partir dos dados do arquivo
            byte[] hashBytes = digest.digest();

            // Converte o hash em uma representação hexadecimal
            StringBuilder hashBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                // Formata cada byte como um número hexadecimal de 2 dígitos
                hashBuilder.append(String.format("%02x", hashByte));
            }

            // Retorna o hash SHA-256 como uma string em hexadecimal
            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo de hash não suportado.", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao calcular o hash do arquivo CSV.", e);
        }
    }
}
