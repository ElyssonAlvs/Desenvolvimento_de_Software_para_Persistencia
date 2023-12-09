package org.SeuCarro;

import org.SeuCarro.Entity.Carro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLer {
    private static final String NOME_ARQUIVO_CSV = "carros.csv";

    // Método para ler dados do arquivo CSV e retorná-los como uma lista de objetos Carro
    public static List<Carro> lerDados() {
        List<Carro> carros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO_CSV))) {
            // Pular o cabeçalho
            br.readLine();

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");

                if (partes.length == 7) {
                    int id = Integer.parseInt(partes[0]);
                    String modelo = partes[1];
                    String marca = partes[2];
                    String configuracao = partes[3];
                    int anoFabricacao = Integer.parseInt(partes[4]);
                    String tipoCombustivel = partes[5];

                    // Ajuste: substituir ',' por '.' no preço antes de fazer o parse
                    double preco = Double.parseDouble(partes[6].replace(',', '.'));

                    Carro carro = new Carro(id, modelo, marca, configuracao, anoFabricacao, tipoCombustivel, preco);
                    carros.add(carro);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }

        // Retorna a lista de carros lidos do arquivo CSV
        return carros;
    }
}
