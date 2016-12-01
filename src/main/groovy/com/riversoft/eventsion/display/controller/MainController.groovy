package com.riversoft.eventsion.display.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.riversoft.eventsion.display.models.SessionModel
import com.riversoft.eventsion.display.repository.SessionRepository
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

    @Autowired
    SessionRepository sessionRepository

    public void receiveData(Connection connection, byte[] data) {
        String s = new String(data)
        log.debug("Message ${s}")
        if (s.contains("mac")) {
            sessionRepository.sessions.put(s, new SessionModel(
                    connection: connection,
                    mac: s
            ))
            connection.send("ok".getBytes())
        }
        String answer = "200"
    }

    public void connect(Connection connection) {
        System.out.println("New connection " + connection.getAddress().getCanonicalHostName())
    }

    public void disconnect(Connection connection) {
        System.out.println("Disconnect " + connection.getAddress().getCanonicalHostName())
    }
}
