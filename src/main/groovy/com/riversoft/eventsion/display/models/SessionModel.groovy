package com.riversoft.eventsion.display.models

import javagrinko.spring.tcp.Connection


class SessionModel {

    Connection connection
    String mac
    String message
    Boolean isMessage = false

}
