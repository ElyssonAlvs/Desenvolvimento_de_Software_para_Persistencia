package org.SeuCarro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.SeuCarro.Entity.Carro;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonData {
    // Nome do arquivo JSON de saída
    private static final String NOME_ARQUIVO_JSON = "carros.json";

    // Método para converter dados do arquivo CSV para JSON
    public static void converterParaJson(List<Carro> carros) {
        try {
            // Configura o ObjectMapper para formatar o JSON de forma mais legível
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Escreve o JSON no arquivo de saída
            try {
                objectMapper.writeValue(new File(NOME_ARQUIVO_JSON), carros);
                System.out.println("Dados convertidos para JSON com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao escrever o arquivo JSON: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro ao converter dados para JSON: " + e.getMessage());
        }
    }
}
