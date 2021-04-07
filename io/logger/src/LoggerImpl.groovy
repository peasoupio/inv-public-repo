class LoggerImpl {

    static Closure console

    void info(String message) {
        console("[${caller.name}] [INFO] ${message}")
    }

    void warn(String message) {
        console("[${caller.name}] [WARN] ${message}")
    }

    void error(String message) {
        console("[${caller.name}] [ERROR] ${message}")
    }

}
