package org.SeuCarro;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.SeuCarro.Entity.Carro;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlData {
    // Nome do arquivo XML de saída
    private static final String NOME_ARQUIVO_XML = "carros.xml";

    // Método para converter dados do arquivo CSV para XML
    public static void converterParaXml(List<Carro> carros) {
        try {
            // Configura o XmlMapper para formatar a saída com identação correta
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Escreve o XML no arquivo de saída especificado
            try {
                xmlMapper.writeValue(new File(NOME_ARQUIVO_XML), carros);
                System.out.println("Dados convertidos para XML com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao escrever o arquivo XML: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao converter dados para XML: " + e.getMessage());
        }
    }
}
