import java.io.IOException;
import java.util.Properties;

public class Exemplo14LoadPropertiesClasspath {
  public static void main(String[] args) {
    Properties prop = new Properties();

    try {
      prop.load(Exemplo14LoadPropertiesClasspath.class.getClassLoader()
                .getResourceAsStream("config.properties"));

      System.out.println(prop.getProperty("database"));
      System.out.println(prop.getProperty("dbuser"));
      System.out.println(prop.getProperty("dbpassword"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
