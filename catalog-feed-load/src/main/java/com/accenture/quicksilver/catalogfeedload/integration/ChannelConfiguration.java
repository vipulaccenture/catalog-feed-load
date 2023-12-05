package com.accenture.quicksilver.catalogfeedload.integration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class ChannelConfiguration {
    @Bean
    public MessageChannel catalogReadChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel aggregationChannel() {
        return new PublishSubscribeChannel();
    }
    @Bean
    public MessageChannel translationChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel persistenceChannel() {
        return new DirectChannel();
    }

}
