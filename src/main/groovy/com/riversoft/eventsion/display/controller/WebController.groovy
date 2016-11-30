package com.riversoft.eventsion.display.controller

import com.riversoft.eventsion.display.repository.SessionModel
import com.riversoft.eventsion.display.service.MessageService
import groovy.util.logging.Slf4j
import javagrinko.spring.tcp.Connection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import java.security.MessageDigest

@Controller
@Slf4j
class WebController {

    @Autowired MessageService messageService

    @RequestMapping('/')
    def home(Model model){
        return 'home'
    }

    @RequestMapping(value = '/', method = RequestMethod.POST)
    def home(Model model, @RequestParam String message){

        log.info(message)
        println ("Send " + message)
        messageService.sendToAll(message)
        return 'home'
    }
}
