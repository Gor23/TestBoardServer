package com.riversoft.eventsion.display.repository

import com.riversoft.eventsion.display.models.SessionModel
import org.springframework.stereotype.Service

import java.util.concurrent.ConcurrentHashMap


@Service
class SessionRepository {

    ConcurrentHashMap<String, SessionModel> sessions = [:]

    List <SessionModel> getAllSessions (){

        List <SessionModel> outList = []
        sessions.each{
            outList.add(it.value)
        }

        return outList
    }

}
