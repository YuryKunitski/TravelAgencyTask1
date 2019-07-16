package by.epam.kunitski.model.dao.dbconfig;

import by.epam.kunitski.model.dao.daoimpl.CountryDAOImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan( basePackageClasses = {CountryDAOImpl.class})
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
