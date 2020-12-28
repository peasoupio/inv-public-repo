
get "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/exec/repo.yml"

inv {

    name "docker"

    require { Exec }

    broadcast { Docker } using {
        ready {
            def exec = $exec as $Exec


            return [
                exists: { String imageName ->
                    assert imageName, "Image name is required"

                    String returnValue = exec.params(
                            returnStdout: true,
                            script: "docker images -q ${imageName}",
                            basedir: new File($0).parentFile.absolutePath)

                    return !returnValue.isEmpty()
                },
                build: { File dockerFile, String imageName ->
                    assert dockerFile, 'Dockerfile is required'
                    assert dockerFile.exists(), 'Dockerfile must be present on filesystem'

                    return exec.params(
                            returnStatus: true,
                            script: "docker build -t ${imageName} ./",
                            basedir: dockerFile.parent) == "0"
                }
        ]}
    }

}