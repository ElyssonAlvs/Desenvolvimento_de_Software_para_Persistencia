package org.SeuCarro;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.SeuCarro.Entity.Carro;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlData {
    // Método para converter dados do arquivo CSV para XML
    public static void converterParaXml(List<Carro> carros, String nomeArquivo) throws IOException {
        try{
        // Configura o XmlMapper para formatar a saída com identação correta
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Escreve o XML no arquivo de saída especificado
        xmlMapper.writeValue(new File(nomeArquivo), carros);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}
