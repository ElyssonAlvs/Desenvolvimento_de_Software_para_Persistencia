/*
Crie uma classe Java de nome SerializaJava para instanciar objetos
da classe definida na Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da própria API Java
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SerializaJava {
    public static void main (String[] ags) throws IOException{
        // Cria uma lista de objetos da classe Carro
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1, "Camry", "Toyota", 2016));
        carros.add(new Carro(2, "Civic", "Honda", 2021));

        try {
            // Serializa a lista de carros em um arquivo
            FileOutputStream fileOut = new FileOutputStream("carros.ser"); // Cria o arquivo
            ObjectOutputStream out = new ObjectOutputStream(fileOut); // gravar no arquivo criado

            // Grava a lista de carros no arquivo
            out.writeObject(carros);

            // Sucesso
            System.out.println("Objetos serializas e salvos no arquivo .ser");
            out.close();
        }catch (IOException e){
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
