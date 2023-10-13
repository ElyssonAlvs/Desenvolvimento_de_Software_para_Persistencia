package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import main.Config;

public class ConnectionFactory {

    // Este método obtém uma conexão com o banco de dados
    public static Connection getConnection() {
        Properties props = Config.getConfig(); // Obtém as configurações de propriedades do banco de dados a partir de um arquivo de configuração

        System.out.println(props.getProperty("database.url")); // Exibe a URL do banco de dados
        System.out.println(props.getProperty("database.username")); // Exibe o nome de usuário do banco de dados
        System.out.println(props.getProperty("database.password")); // Exibe a senha do banco de dados

        Connection connection = null; // Inicializa a conexão como nula

        try {
            // Tenta estabelecer uma conexão com o banco de dados usando as informações de URL, nome de usuário e senha fornecidas nas propriedades
            connection = DriverManager.getConnection(
                    props.getProperty("database.url"),
                    props.getProperty("database.username"),
                    props.getProperty("database.password")
            );
        } catch (SQLException e) {
            // Exceções de SQLException
            e.printStackTrace(); // Isso imprime informações sobre o erro no console
        }

        return connection; // Retorna a conexão, que pode ser nula se ocorrer um erro
    }
}
