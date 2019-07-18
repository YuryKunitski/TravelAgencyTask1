package by.epam.kunitski.travelagency.dao.dbconfig;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.epam.kunitski")
public class TestConfig {

    @Bean("dataSource")
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("db/migration/V1_0__create_shema.sql")
                .addScript("db/migration/V1_1__create_tables.sql")
                .addScript("db/migration/V1_2__input_DB.sql")
                .build();
        return database;
    }

    @Bean("flyway")
    public Flyway getFlyway() {
        FluentConfiguration configuration = Flyway.configure().dataSource(getDataSource());
        configuration.baselineOnMigrate(true);
        return new Flyway(configuration);
    }


}
