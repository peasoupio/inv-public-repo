class ExecOptions {

    private final static int DEFAULT_TIMEOUT_MS = 1000 * 60 * 5 // 5 mins

    private final systemVars = System.getenv().collect { k, v -> "$k=$v" }
    private final ExecOutput errOutput = new ExecOutput()
    private final ExecOutput infoOutput = new ExecOutput()

    String command
    String basedir = "."
    boolean returnStdout = false
    boolean returnStatus = false
    int timeoutMs = 0
    List vars = []
    boolean includeSystemProperties = false
    boolean fireAndForget = false

    Object execute() {
        if (!command)
            throw new IllegalArgumentException("commands")

        infoOutput.append "[EXEC] [CALL] ${basedir}/${command}"

        List processVars = vars

        if (includeSystemProperties)
            processVars += systemVars

        Process proc

        if (basedir)
            proc = command.execute(processVars, new File(basedir))
        else
            proc = command.execute(processVars, null)

        if (fireAndForget)
            return null

        StringBuilder output =  new StringBuilder()

        def execOutput = new ExecOutput(output: output)

        if (timeoutMs == 0)
            proc.waitForProcessOutput(execOutput, errOutput)
        else
            proc.waitForOrKill(timeoutMs)

        def exitValue = proc.exitValue()

        // If a non-zero exit value is provided, log
        if (exitValue != 0)
            infoOutput.append "[EXEC] Return value: ${exitValue}"

        if (returnStatus)
            return exitValue

        if (returnStdout)
            return output.toString()?.trim()

        // If a non-zero exit value is provided, return false
        if (exitValue != 0)
            return false

        // Otherwise, return true
        return true
    }
}
