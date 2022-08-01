package com.sofka.broker.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/broker/topic/")
public class RabbitMQWebController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/adminToAllFloors")
    public String adminToAllFloors(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                        @RequestParam("messageData") String messageData) {

        amqpTemplate.convertAndSend(exchange, routingKey, messageData);

        return "Message sent to the RabbitMQ Topic Exchange Successfully - adminToAllFloors";
    }

    @GetMapping(value = "/adminToOddFloors")
    public String adminToOddFloors(@RequestParam("exchangeName") String exchange,
                           @RequestParam("messageData") String messageData) {

        String[] floorArr = new String[] {"10*", "20*", "30*", "40*", "50*", "60*", "70*","80*","90*"};

        for (int i = 1; i<= floorArr.length; i++) {
            if (i%2 != 0) {
                amqpTemplate.convertAndSend(exchange, "queue."+floorArr[i-1], messageData);
            }
        }
        return "Message sent to the RabbitMQ Topic Exchange Successfully - adminToOddFloors";
    }

}
