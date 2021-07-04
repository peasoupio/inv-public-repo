repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/exec/repo.yml"

inv {

    name "docker"

    require { Exec }

    broadcast { Docker(local: true) } using {
        global { return new LocalDockerHandler($exec as $Exec) }
    }

}