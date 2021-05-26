class ExecHandler {

    /**
     * Execute
     * @param command
     * @return
     */
    Boolean simple(String command) {
        return new ExecOptions(command: command).execute() as Boolean
    }

    /**
     * Execute a process using a set of options.
     * Available options:
     * - String command
     * - String basedir = "."
     * - boolean returnStdout = false
     * - boolean returnStatus = false
     * - int timeoutMs = DEFAULT_TIMEOUT_MS
     * - List vars = []
     * - boolean includeSystemProperties = false
     * - boolean fireAndForget = false
     *
     * If no return options has been turned on, returns "true" is a zero exit code has been returned by the command.
     * Otherwise, returns false.
     *
     * @param opts The options
     * @return Expected value depending the provided options or default behaviour (see above).
     */
    Object params(Map opts) {
        return new ExecOptions(opts).execute()
    }
}
