package org.SeuCarro;

import org.SeuCarro.Entity.Carro;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static java.lang.String.format;

public class CsvData {
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Adiciona um cabeçalho ao arquivo CSV
    static {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO_CSV, true))) {
            writer.println("ID,Modelo,Marca,Configuracao,AnoFabricacao,TipoCombustivel,Preco");
        } catch (IOException e) {
            System.out.println("Erro ao adicionar cabeçalho ao arquivo CSV: " + e.getMessage());
        }
    }

    public static void inserirCarro(Carro carro) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO_CSV, true))) {
            // Garante que o preço seja formatado corretamente (ponto como separador decimal)
            String precoFormatado = format(Locale.US, "%.2f", carro.getPreco());

            String linha = format("%d,%s,%s,%s,%d,%s,%s",
                    carro.getId(),
                    carro.getModelo(),
                    carro.getMarca(),
                    carro.getConfiguracao(),
                    carro.getAnoFabricacao(),
                    carro.getTipoCombustivel(),
                    precoFormatado);

            writer.println(linha);
            System.out.println("Carro inserido com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao inserir o carro no arquivo CSV: " + e.getMessage());
        }
    }
}
