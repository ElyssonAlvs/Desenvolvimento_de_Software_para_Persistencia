package org.SeuCarro;

import java.io.*;

public class CsvDataContador {
    // Diratório e nome do arquivo CSV que será lido
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para contar entidades no arquivo CSV
    public static int contarEntidades() throws IOException {
        // Utilizamos o try-with-resources para garantir que o BufferedReader seja fechado corretamente
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO_CSV))) {
            int contador = 0; // Inicializamos um contador para contar as entidades
            // Lê cada linha do arquivo CSV até que não haja mais linhas
            while ((br.readLine()) != null) {
                // Cada linha no arquivo representa uma entidade, então incrementamos o contador
                contador++;
            }

            // Retornamos o número total de entidades encontradas no arquivo
            return contador;
        }
    }
}
