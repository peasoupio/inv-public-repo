class ExecHandler {
    Boolean simple(String command) {
        return new ExecOptions(command: command).execute() as Boolean
    }

    Object params(Map opts) {
        return new ExecOptions(opts).execute()
    }
}
