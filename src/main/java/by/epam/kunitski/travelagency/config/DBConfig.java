package by.epam.kunitski.travelagency.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.epam.kunitski.travelagency")
class DBConfig {

    private static final String CP_PROPERTIES = "/hikari.properties";

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig(CP_PROPERTIES);
        return new HikariDataSource(hikariConfig);

    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


}
