package org.SeuCarro;

import java.io.FileWriter;
import java.io.*;
import java.io.PrintWriter;

import static java.lang.String.format;

public class CsvData {
    /*
    Define uma constante que representa o caminho do arquivo CSV
    onde os dados dos carros serão armazenados.
     */
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para inserir um objeto Carro no arquivo CSV
    public static void inserirCarro(Carro carro) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO_CSV, true))) {
            // Abre o arquivo CSV no modo de acrescentar (append) para adicionar novos dados

            // Constrói uma linha CSV com os dados do carro
            String linha = format("%d,%s,%s,%s,%d,%s,%d",
                    carro.getIdCarro(),            // ID do carro
                    carro.getModelo(),             // Modelo do carro
                    carro.getMarca(),              // Marca do carro
                    carro.getConfiguracao(),       // Configuração do carro
                    carro.getAnoFabricacao(),      // Ano de fabricação do carro
                    carro.getTipoCombustivel(),    // Tipo de combustível do carro
                    carro.getpreco());             // Preço do carro

            // Escreve a linha no arquivo CSV
            writer.println(linha);

            System.out.println("Carro inserido com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao inserir o carro no arquivo CSV: " + e.getMessage());
        }
    }
}
