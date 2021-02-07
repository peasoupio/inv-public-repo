@groovy.transform.Field
def systemVars = System.getenv().collect { k, v -> "$k=$v" }

@groovy.transform.Field
ExecOutput errOutput = new ExecOutput(console: { debug(it) })

inv {
    name "exec"

    broadcast { Exec } using {

        ready {[
                simple: { String script ->
                    return doExec(script)
                },
                params: { Map params ->
                    return doExec(
                            params.script,
                            params.basedir,
                            params.returnStdout ?: false,
                            params.returnStatus ?: false,
                            params.vars ?: [],
                            params.delegateEnvVars ?: false,
                            params.fireAndForget ?: false
                    )
                }
        ]}

    }

}

String doExec(def script, String basedir = ".", boolean returnStdout = false, boolean returnStatus = false, List vars = [], boolean delegateEnvVars = false, boolean fireAndForget = false) {

    debug "[EXEC] [CALL] ${basedir}/${script}"

    List processVars = vars

    if (delegateEnvVars) {
        processVars += systemVars
    }

    Process proc

    if (basedir)
        proc = script.execute(processVars, new File(basedir))
    else
        proc = script.execute(processVars, null)

    if (fireAndForget) {
        return null
    }

    StringBuilder output =  new StringBuilder()

    def execOutput = new ExecOutput(output: output, console: { debug(it) })
    proc.waitForProcessOutput(execOutput, errOutput)

    // TODO Overkill avec waitForProcessOutput ?
    proc.waitForOrKill(300 * 1000)


    def exitValue = proc.exitValue()

    if (exitValue) {
        debug "[EXEC] Return value: ${exitValue}"
    }

    if (returnStatus)
        return exitValue

    if (returnStdout)
        return output.toString()
}

class ExecOutput implements Appendable {

    StringBuilder output
    Closure console

    @Override
    Appendable append(CharSequence charSequence) throws IOException {

        if (output != null)
            output.append(charSequence)

        if (!charSequence.equals("\n") && charSequence.length() > 0)
            console(charSequence)
    }

    @Override
    Appendable append(CharSequence charSequence, int i, int i1) throws IOException {

        if (output != null)
            output.append(charSequence, i, i1)

        if (!charSequence.equals("\n") && charSequence.length() > 0)
            console(charSequence.subSequence(i, i1))
    }

    @Override
    Appendable append(char c) throws IOException {

        if (output != null)
            output.append(c)

        console(c)
    }
}