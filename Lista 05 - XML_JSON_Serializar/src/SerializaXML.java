/*
Crie uma classe Java de nome SerializaXML para instanciar objetos da
classe definida na Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da biblioteca Jackson.
 */
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializaXML {
    public static void main(String[] args) throws IOException {
        // Criando uma lista de carros
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1, "Civic", "Honda", 2021));
        carros.add(new Carro(2, "Camry", "Toyota", 2016));
        carros.add(new Carro(3, "Gol Cli", "Volkswagen", 1994));

        try {
            // Configurando o ObjectMapper para lidar com XML
            XmlMapper xmlMapper = new XmlMapper();

            // Convertendo a lista de carros para formato XML
            String xml = xmlMapper.writeValueAsString(carros);

            // Escrevendo o XML no arquivo
            try (FileWriter writer = new FileWriter("carros.xml")) {
                writer.write(xml);
            }catch(IOException e){
                System.out.println("Erro " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Serialização para XML concluida.");
        } catch (IOException e) {
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
