package com.riversoft.eventsion.display.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.riversoft.eventsion.display.repository.InitModel
import com.riversoft.eventsion.display.service.MessageService
import groovy.util.logging.Slf4j
import javagrinko.spring.tcp.Connection
import javagrinko.spring.tcp.TcpController
import org.springframework.beans.factory.annotation.Autowired

@Slf4j
@TcpController
class MainController {

    @Autowired
    MessageService messageService

    @Autowired
    ObjectMapper mapper

    public void receiveData(Connection connection, byte[] data) {
        String s = new String(data);
        log.info("Input data: ${data.toString()}")
        System.out.println("Input data: ${s}")
        if (s.contains("mac")) {
            InitModel initModel = mapper.readValue(s, InitModel)
            messageService.addSession(initModel.mac, connection)
            //connection.send("ok".getBytes())
        }
    }

    public void connect(Connection connection) {
        System.out.println("New connection " + connection.getAddress().getCanonicalHostName());
    }

    public void disconnect(Connection connection) {
        System.out.println("Disconnect " + connection.getAddress().getCanonicalHostName());
    }


}
