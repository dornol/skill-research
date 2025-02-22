package dev.dornol.jooq.config;

import org.jooq.conf.ExecuteWithoutWhere;
import org.jooq.tools.LoggerListener;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

    @Bean
    public DefaultConfigurationCustomizer jooqConfigurationCustomizer() {
        return configuration -> {
            configuration.settings()
                    .withExecuteDeleteWithoutWhere(ExecuteWithoutWhere.THROW)
                    .withExecuteUpdateWithoutWhere(ExecuteWithoutWhere.THROW)
                    .withRenderSchema(false)
                    .withRenderFormatted(true)
                    .withBatchSize(100)
                    .withQueryTimeout(60);
            configuration.set(new LoggerListener());
        };
    }

}
