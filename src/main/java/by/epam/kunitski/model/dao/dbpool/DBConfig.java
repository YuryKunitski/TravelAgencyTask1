package by.epam.kunitski.model.dao.dbpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan("by.epam.kunitski")
@PropertySource("classpath:hikari.properties")
public class DBConfig {

    private static final String CP_PROPERTIES = "/hikari.properties";

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig(CP_PROPERTIES);
        return new HikariDataSource(hikariConfig);

    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }


}
