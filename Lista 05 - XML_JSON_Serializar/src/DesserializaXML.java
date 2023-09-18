/*
 * Crie uma classe java de nome DesserializaXML para ler / 
 * desserializar os objetos Serializados na Questão 4 e exibi-los.
 */
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DesserializaXML {
    public static void main(String[] args) {
        try {
            
            // Cria um XmlMapper, que é uma classe da biblioteca Jackson para lidar com XML
            XmlMapper xmlMapper = new XmlMapper();
            
            // Lê o arquivo XML contendo a lista serializada de carros e desserializa-a diretamente em uma lista de Map<String, Object>
            List<Map<String, Object>> carros = xmlMapper.readValue(new File("carros.xml"), List.class);
            
            // Itera sobre a lista de carros desserializados
            carros.forEach(carro -> {
                // Exibe as informações de cada carro
                System.out.println("ID: " + carro.get("idCarro"));
                System.out.println("Modelo: " + carro.get("modelo"));
                System.out.println("Marca: " + carro.get("marca"));
                System.out.println("Ano de Fabricação: " + carro.get("anoFabricacao"));
                System.out.println();
            });
            
            System.out.println("Desserialização a partir do XML concluída.");
        } catch (IOException e) {
            // Em caso de erro de E/S, exibe uma mensagem de erro e rastreamento de pilha
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
