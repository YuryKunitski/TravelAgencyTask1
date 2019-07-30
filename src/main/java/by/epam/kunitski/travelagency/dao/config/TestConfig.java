package by.epam.kunitski.travelagency.dao.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.epam.kunitski.travelagency")
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("db/migration/V1_0__create_shema.sql")
                .addScript("db/migration/V1_1__create_tables.sql")
                .addScript("db/migration/V1_2__input_DB.sql")
                .build();
        return database;
    }

    @Bean(name = "flyway")
    public Flyway getFlyway() {
        FluentConfiguration flywayConfiguration = Flyway.configure().dataSource(dataSource());
        flywayConfiguration.baselineOnMigrate(true);
        return new Flyway(flywayConfiguration);
    }
}
