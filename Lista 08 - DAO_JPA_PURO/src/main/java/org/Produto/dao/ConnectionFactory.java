package org.Produto.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.Produto.main.Config;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionFactory {

    public static Properties props;
    public static DataSource ds;

    // Inicialização estática que lê as configurações do banco de dados.
    static {
        props = Config.getConfig();
        log.info("database.url: {}", props.getProperty("database.url"));
        log.info("database.username: {}", props.getProperty("database.username"));
        log.info("database.password: {}", props.getProperty("database.password"));
        log.info("persistence.unit: {}", props.getProperty("persistence.unit"));
    }

    // Obtém uma conexão com o banco de dados.
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    // Obtém um objeto DataSource configurado para o banco de dados.
    public static DataSource getDataSource() {
        if (ds == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("database.url"));
            config.setUsername(props.getProperty("database.username"));
            config.setPassword(props.getProperty("database.password"));

            // Define o tamanho máximo do pool de conexões, se configurado.
            String prop = props.getProperty("database.max_poll_size");
            if (prop != null)
                config.setMaximumPoolSize(Integer.parseInt(prop));

            // Define o número mínimo de conexões ociosas no pool, se configurado.
            prop = props.getProperty("database.min_idle");
            if (prop != null)
                config.setMinimumIdle(Integer.parseInt(props.getProperty("database.min_idle")));

            // Cria e configura o DataSource usando o HikariCP.
            ds = new HikariDataSource(config);
        }
        return ds;
    }
}
