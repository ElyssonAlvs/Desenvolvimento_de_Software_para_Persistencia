import java.io.*;
import java.util.zip.ZipInputStream;

public class LeituraZIP {
    public static void main(String[] args) throws IOException {
        try {
            // Pegar o arquivo em modo leitura
            InputStream is = new FileInputStream("teste.zip");
            // Leitura do arquivo em .zip
            ZipInputStream zis = new ZipInputStream(is);
            // Leitura do proximo arquivos .zip
            zis.getNextEntry();
            // Leitura do arquivos em modo de leitura
            InputStreamReader isr = new InputStreamReader(zis);
            // Colocar em um buffer
            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println("Erro : " + e.getMessage());
            e.getStackTrace();
        }
    }
}
