repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/exec/repo.yml"

inv {

    name "kubernetes"

    require { Exec }

    broadcast { Kubernetes(local: true) } using {
        global { return new LocalKubernetesHandler(exec: $exec as $Exec) }
    }

}