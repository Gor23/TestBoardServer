package com.riversoft.eventsion.display.service

import com.riversoft.eventsion.display.repository.SessionModel
import com.riversoft.eventsion.display.repository.SessionRepository
import groovy.util.logging.Slf4j
import javagrinko.spring.tcp.Connection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class MessageService {

    @Autowired
    SessionRepository sessionRepository

    String message
    Boolean isMessage

    def writeMessage(String inputMessage){

        message = inputMessage
        isMessage = true
    }

    String readMessage(){

        isMessage = false
        return message
    }

    void addSession (String mac, Connection connection){
        sessionRepository.sesions.put(mac, new SessionModel(
                mac:mac,
                connection:connection
        ))
    }

    List<SessionModel> getAllSesions (){

        List<SessionModel> models = []
        sessionRepository.sesions.each {
            models.add(it.value)
        }
        return models
    }

    void sendToAll(String message){
        List<SessionModel> models = getAllSesions()
        models.each{
            it.connection.send(message.getBytes())
        }
    }

}
