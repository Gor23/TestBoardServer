package com.riversoft.eventsion.display.repository

import org.springframework.stereotype.Service

import java.util.concurrent.ConcurrentHashMap


@Service
class SessionRepository {

    ConcurrentHashMap<String, SessionModel> sesions = [:]

}
