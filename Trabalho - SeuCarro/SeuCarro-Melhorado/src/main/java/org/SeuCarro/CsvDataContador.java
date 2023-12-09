package org.SeuCarro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvDataContador {
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para contar entidades no arquivo CSV
    public static int contarEntidades() {
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO_CSV))) {
            int contador = 0;

            // Pular o cabeçalho
            br.readLine();

            // Lê cada linha do arquivo CSV até que não haja mais linhas
            while (br.readLine() != null) {
                // Cada linha no arquivo representa uma entidade, então incrementamos o contador
                contador++;
            }

            return contador;
        } catch (IOException e) {
            System.out.println("Erro ao contar entidades no arquivo CSV: " + e.getMessage());
            return 0; // Retorna 0 em caso de erro
        }
    }
}
