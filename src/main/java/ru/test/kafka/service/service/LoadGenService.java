package ru.test.kafka.service.service;

import org.springframework.transaction.annotation.Transactional;
import ru.test.kafka.service.model.LoadGenTable;

/**
 * Service class for {@link LoadGenTable}
 *
 * @author Vladislav K.
 * @version 1.0
 */

public interface LoadGenService {

    @Transactional
    void save(LoadGenTable user);

}
