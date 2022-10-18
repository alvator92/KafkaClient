package ru.test.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.*;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.test.kafka.configuration.kafka.KafkaConfig;
import ru.test.kafka.service.response.RespController;

import javax.websocket.SendResult;

@Controller
@Slf4j
public class MsgController {

    @Autowired
    private RespController controller;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaConfig kafkaConfig;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {return "Welcome to KafkaReplicator test app. Enjoy it!";}

    /**
     * RestController for working with DAO
     */
    @RequestMapping(value = "/{operName}/{num}")
    ResponseEntity<String> Response(@PathVariable String operName,
                                    @PathVariable int num) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");

        // Подготовка ответа и отправка в топик Кафка
        getResult(kafkaTemplate.send(kafkaConfig.getMessage().getTopic_1(), "client_from_DB"));

        return new ResponseEntity<>("HttpStatus.OK",httpHeaders, HttpStatus.OK);
    }

    private synchronized void getResult(ListenableFuture<SendResult<String, String>> send, String message) {
        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("Sent message topic {}} send with offset=[{}}], partition=[{}}]",
                        stringStringSendResult.getMetadata().topic(),
                        stringStringSendResult.getMetadata().offset(),
                        stringStringSendResult.getMetaData().partition());

            }
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Unable to send message=[{}] due to : {}", message, throwable.getMessage());
            }
        });

    }

}
