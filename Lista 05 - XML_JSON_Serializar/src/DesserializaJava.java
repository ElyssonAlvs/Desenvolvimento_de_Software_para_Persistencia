import java.io.*;
import java.util.List;

public class DesserializaJava {
    public static void main(String[] args) {
        try {
            // Abre um arquivo de entrada para leitura da lista serializada
            FileInputStream fileIn = new FileInputStream("carros.ser");

            // Cria um fluxo de entrada de objeto para desserializar a lista a partir do arquivo
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Lê o objeto do arquivo
            Object object = in.readObject();

            // Verifica se o objeto é uma lista de carros antes de fazer a conversão
            if (object instanceof List<?>) {
                List<?> listaDesserializada = (List<?>) object;
                for (Object item : listaDesserializada) {
                    if (item instanceof Carro) {
                        Carro carro = (Carro) item;
                        System.out.println(carro);
                    }
                }
            }

            // Fecha o fluxo de entrada de objeto
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
