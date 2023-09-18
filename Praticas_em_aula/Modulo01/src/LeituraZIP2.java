import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

public class LeituraZIP2 {
    public static void main(String[] args) throws IOException {
        try {
            // Pegar o arquivo em modo leitura
            InputStream is = new FileInputStream("teste.zip");
            // Leitura do arquivo em .zip
            ZipInputStream zis = new ZipInputStream(is);
            zis.getNextEntry();
            Scanner sc = new Scanner(zis);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Erro : " + e.getMessage());
            e.getStackTrace();
        }
    }
}
