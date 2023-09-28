package org.SeuCarro;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipData {
    // Nome do arquivo CSV de entrada
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para compactar o arquivo CSV em um arquivo ZIP
    public static void converterParaZip() throws IOException {
        // Nome do arquivo ZIP de saída
        String nomeArquivoZip = "carros.zip";

        // Preparar o arquivo ZIP
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(nomeArquivoZip))) {
            // Adicionar o arquivo CSV ao ZIP
            ZipEntry zipEntry = new ZipEntry(NOME_ARQUIVO_CSV);
            zipOutputStream.putNextEntry(zipEntry);

            // Ler o arquivo CSV e escrever no ZIP
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(NOME_ARQUIVO_CSV))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Compactação para ZIP concluída: " + nomeArquivoZip + " criado.");
        }
    }
}
