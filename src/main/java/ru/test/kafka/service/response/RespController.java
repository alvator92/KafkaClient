package ru.test.kafka.service.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.kafka.configuration.JpaConfig;
import ru.test.kafka.service.model.LoadGenTable;

/**
 * Class working with DAO {@link JpaConfig}
 *
 * @author Vladislav K.
 * @version 1.0
 */

@Component
public class RespController {
    @Autowired
    private JpaConfig jpaConfig;

    public RespController() {
    }

    /**
     * Отправка в БД
     * @param loadGenTable
     */
    public void sendClientToDB(LoadGenTable loadGenTable) {
        jpaConfig.loadGenService().save(loadGenTable);

    }
}
