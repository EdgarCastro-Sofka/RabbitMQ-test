package com.sofka.broker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue301() {
        return new Queue("301", false);
    }

    @Bean
    Queue queue302() {
        return new Queue("302", false);
    }

    @Bean
    Queue queue303() {
        return new Queue("303", false);
    }

    @Bean
    Queue queue401() {
        return new Queue("401", false);
    }

    @Bean
    Queue queue402() {
        return new Queue("402", false);
    }

    @Bean
    Queue queue403() {
        return new Queue("403", false);
    }

    @Bean
    Queue queue501() { return new Queue("501", false); }

    @Bean
    Queue queue502() { return new Queue("502", false); }

    @Bean
    Queue queue503() { return new Queue("503", false); }

    @Bean
    Queue allQueue() {
        return new Queue("allQueue", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding topicBindingApt301(Queue queue301, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue301).to(topicExchange).with("queue.30*");
    }

    @Bean
    Binding topicBindingApt302(Queue queue302, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue302).to(topicExchange).with("queue.30*");
    }

    @Bean
    Binding topicBindingApt303(Queue queue303, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue303).to(topicExchange).with("queue.30*");
    }

    @Bean
    Binding topicBindingApt401(Queue queue401, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue401).to(topicExchange).with("queue.40*");
    }

    @Bean
    Binding topicBindingApt402(Queue queue402, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue402).to(topicExchange).with("queue.40*");
    }

    @Bean
    Binding topicBindingApt403(Queue queue403, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue403).to(topicExchange).with("queue.40*");
    }

    @Bean
    Binding topicBindingApt501(Queue queue501, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue501).to(topicExchange).with("queue.50*");
    }

    @Bean
    Binding topicBindingApt502(Queue queue502, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue502).to(topicExchange).with("queue.50*");
    }

    @Bean
    Binding topicBindingApt503(Queue queue503, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue503).to(topicExchange).with("queue.50*");
    }

    @Bean
    Binding topicAllBinding(Queue allQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
