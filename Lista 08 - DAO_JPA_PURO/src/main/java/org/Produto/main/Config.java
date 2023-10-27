package org.Produto.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    // Método estático que carrega as configurações do arquivo 'config.properties'.
    public static Properties getConfig() {
        try {
            // Tenta carregar as configurações a partir do arquivo 'config.properties'.
            props.load(Config.class.getResourceAsStream("/config.properties"));

            // Exibe uma mensagem de sucesso e as chaves das configurações carregadas.
            System.out.println("Configurações carregadas com sucesso!");
            System.out.println(props.stringPropertyNames());
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Em caso de erro de arquivo não encontrado, imprime o erro.
        } catch (IOException e) {
            e.printStackTrace(); // Em caso de erro de E/S (IOException), imprime o erro.
        }
        return props; // Retorna as configurações carregadas (ou vazias, se ocorrer algum erro).
    }
}
