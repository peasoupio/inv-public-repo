load "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/exec/repo.yml"

inv {

    name "kubernetes"

    require { Exec }

    broadcast { Kubernetes(local: true) } using {
        ready { return new LocalKubernetesHandler(exec: $exec as $Exec) }
    }

}