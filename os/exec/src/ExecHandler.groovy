class ExecHandler {
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

     * @param opts
     * @return
     */
    Object params(Map opts) {
        return new ExecOptions(opts).execute()
    }
}
