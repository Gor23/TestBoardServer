package com.riversoft.eventsion.display.controller

import com.riversoft.eventsion.display.models.SessionModel
import com.riversoft.eventsion.display.repository.SessionRepository
import com.riversoft.eventsion.display.service.MessageService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@Slf4j
class WebController {

    @Autowired MessageService messageService
    @Autowired
    SessionRepository sessionRepository

    @RequestMapping(value = '/', method = RequestMethod.GET)
    String goToMainMenu(Model model){

        String size = sessionRepository.sessions.size().toString()
        model.addAttribute("sessions", size);
        log.info("Enter to home page")
        return 'home'
    }

    @RequestMapping(value = '/football', method = RequestMethod.POST)
    def sendFootball(Model model){
        String size = sessionRepository.sessions.size().toString()
        model.addAttribute("sessions", size);
        String send = "{\"period\":null,\"team_name_first\":\"team1\",\"team_name_second\":\"team2\",\"score_first\":1,\"score_second\":3,\"game_type\":\"FOOTBALL\",\"action_type\":\"GOAL\"}"
        log.info(send)
        List<SessionModel> sesions = sessionRepository.getAllSessions()
        for (SessionModel s: sesions) {
            s.connection.send(send.getBytes())
        }
        return 'home'
    }

    @RequestMapping(value = '/tenis', method = RequestMethod.POST)
    def sendTenis(Model model){
        String size = sessionRepository.sessions.size().toString()
        model.addAttribute("sessions", size);
        String send = "{\"period\":null,\"team_name_first\":\"team1\",\"team_name_second\":\"team2\",\"score_first\":1,\"score_second\":3,\"winPeriodFirst\":1,\"winPeriodSecond\":2,\"game_type\":\"TENNIS\",\"action_type\":\"POINT\"}"
        log.info(send)
        List<SessionModel> sesions = sessionRepository.getAllSessions()
        for (SessionModel s: sesions) {
            s.connection.send(send.getBytes())
        }
        return 'home'
    }

    @RequestMapping(value = '/logo', method = RequestMethod.POST)
    def sendLogo(Model model){
        log.info("Send logo")
        String size = sessionRepository.sessions.size().toString()
        model.addAttribute("sessions", size);
        String send = "{\"response\":\"NO_GAMES\",\"time\":\"2016-10-26T07:10:00.000Z\"}"
        log.info(send)
        List<SessionModel> sesions = sessionRepository.getAllSessions()
        for (SessionModel s: sesions) {
            s.connection.send(send.getBytes())
        }
        return 'home'
    }

    @RequestMapping(value = '/winner', method = RequestMethod.POST)
    def sendWinner(Model model){
        log.info("Send winner")
        String size = sessionRepository.sessions.size().toString()
        model.addAttribute("sessions", size);
        String send = "{\"action_type\":\"WINNER\"}"
        log.info(send)
        List<SessionModel> sesions = sessionRepository.getAllSessions()
        for (SessionModel s: sesions) {
            s.connection.send(send.getBytes())
        }
        return 'home'
    }



}
