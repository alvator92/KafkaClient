package ru.test.kafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.test.kafka.service.service.LoadGenService;
import ru.test.kafka.service.service.LoadGenServiceImpl;


/**
 * Class for {@link ru.test.kafka.service.service.LoadGenServiceImpl}
 *
 * @author Vladislav K.
 * @version 1.0
 */

@Configuration
@EnableJpaRepositories(basePackages = {"ru.test.kafka.service.repository"},
        entityManagerFactoryRef = "apossEntityManager",
        transactionManagerRef = "apossTransactionManager")
public class JpaConfig {

    @Bean("LoadGenService")
    public LoadGenService loadGenService() {
        return new LoadGenServiceImpl();
    }

}
