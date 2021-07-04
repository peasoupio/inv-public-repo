inv {

    name "io.logger"

    // Create a console closure
    LoggerImpl.console = { String message -> debug(message) }

    broadcast { Logger } using {
        global { new LoggerImpl() }
    }
}