import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonData {
    // Nome do arquivo JSON de saída
    private static final String NOME_ARQUIVO_JSON = "carros.json";

    // Método para converter dados do arquivo CSV para JSON
    public static void converterParaJson(List<Carro> carros, String NomeArquivo) throws IOException {
        // Configura o ObjectMapper para formatar o JSON de forma mais legível
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Escreve o JSON no arquivo de saída
        objectMapper.writeValue(new File(NOME_ARQUIVO_JSON), carros);

        System.out.println("Conversão para JSON concluída.");
    }
}
