package com.riversoft.eventsion.display.configuration

import javagrinko.spring.starter.TcpServerProperties
import javagrinko.spring.tcp.Server;
import javagrinko.spring.tcp.TcpControllerBeanPostProcessor;
import javagrinko.spring.tcp.TcpServer;
import javagrinko.spring.tcp.TcpServerAutoStarterApplicationListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TcpServerProperties.class)
//@ConditionalOnProperty(prefix = "javagrinko.tcp-server", name = {"port", "autoStart"})
class TcpConf {

    @Bean
    TcpServerAutoStarterApplicationListener tcpServerAutoStarterApplicationListener() {
        return new TcpServerAutoStarterApplicationListener()
    }

    @Bean
    TcpControllerBeanPostProcessor tcpControllerBeanPostProcessor() {
        return new TcpControllerBeanPostProcessor()
    }

    @Bean
    Server server(){
        return new TcpServer()
    }
}
