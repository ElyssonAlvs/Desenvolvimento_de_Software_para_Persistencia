package org.SeuCarro;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipData {
    // Nome do arquivo CSV de entrada
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Nome do arquivo ZIP de saída
    private static final String NOME_ARQUIVO_ZIP = "carros.zip";

    // Método para compactar o arquivo CSV em um arquivo ZIP
    public static void converterParaZip() {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(NOME_ARQUIVO_ZIP));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(NOME_ARQUIVO_CSV))) {

            // Adicionar o arquivo CSV ao ZIP
            ZipEntry zipEntry = new ZipEntry(NOME_ARQUIVO_CSV);
            zipOutputStream.putNextEntry(zipEntry);

            // Ler o arquivo CSV e escrever no ZIP
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Arquivo CSV compactado para ZIP com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo CSV não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao compactar o arquivo CSV para ZIP: " + e.getMessage());
        }
    }
}
