
inv {
    name "exec"

    broadcast { Exec } using {
        ready {

            // Set de debug closure as the output console method
            ExecOutput.console = { String msg -> debug(msg) }

            return new ExecHandler()
        }
    }
}


