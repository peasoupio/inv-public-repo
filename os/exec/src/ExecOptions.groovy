class ExecOptions {

    private final static List SYSTEM_VARIABLES = System.getenv().collect { k, v -> "$k=$v" }
    private final static ExecOutput ERROR_OUTPUT = new ExecOutput()

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

        List processVars = vars

        if (includeSystemProperties)
            processVars += SYSTEM_VARIABLES

        Process proc

        if (basedir)
            proc = command.execute(processVars, new File(basedir))
        else
            proc = command.execute(processVars, null)

        if (fireAndForget)
            return null

        ExecOutput execOutput

        // If returnStdout enabled, capture output (and print it)
        // Otherwise, don't print it
        if (returnStdout) {
            StringBuilder output =  new StringBuilder()
            execOutput = new ExecOutput(output: output)
        } else {
            execOutput = new ExecOutput(print: false)
        }

        // Print command
        execOutput.append(">>> " + command)

        // If timeout is defined, wait for it.
        // Otherwise, wait until output is done.
        if (timeoutMs == 0)
            proc.waitForProcessOutput(execOutput, ERROR_OUTPUT)
        else
            proc.waitForOrKill(timeoutMs)

        // Get process exitValue
        def exitValue = proc.exitValue()

        if (returnStatus)
            return exitValue

        if (returnStdout)
            return execOutput.output.toString()?.trim()

        // If a non-zero exit value is provided, return false
        if (exitValue != 0)
            return false

        // Otherwise, return true
        return true
    }
}
