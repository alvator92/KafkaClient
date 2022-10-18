package ru.test.kafka.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Class Entity
 *
 * @author Vladislav K.
 * @version 1.0
 */

@Entity
@Table(name = "load_gen_service")
@Data
public class LoadGenTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private String oper_code;

    @Column(nullable = false)
    private String oper_name;

    @Column(nullable = false)
    private String personal_id;

    @Column(nullable = false)
    private String owner_id;

    @DateTimeFormat(pattern = "yyyy.MM.dd HH-mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH-mm")
    @Column(name = "reg_time", columnDefinition = "DATETIME", nullable = false)
    private Date reg_time;
}
