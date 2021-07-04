repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/logger/repo.yml"

inv {
    name "os.exec"

    require { Logger }

    broadcast { Exec } using {
        global {

            // Set de debug closure as the output console method
            ExecOutput.logger = $logger as $Logger

            return new ExecHandler()
        }
    }
}


