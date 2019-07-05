package by.epam.kunitski.model.dao.dbpool;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@ComponentScan("by.epam.kunitski")
@PropertySource("classpath:hikari.properties")
public class DBConfig {

    @Autowired
    private static Environment environment;

    @Bean(destroyMethod = "close")
    public String dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(environment.getProperty("db.jdbcDriver"));
        System.out.println(environment.getProperty("db.jdbcDriver"));
        return environment.getProperty("db.jdbcDriver");
    }


}
