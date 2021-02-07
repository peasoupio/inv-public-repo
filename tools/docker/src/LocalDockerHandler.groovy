class LocalDockerHandler {

    private final static File TMP_DIR = new File(System.getProperty("java.io.tmpdir"))

    private final $Exec exec

    LocalDockerHandler($Exec exec) {
        assert exec, "Exec is required"

        this.exec = exec
    }

    boolean exists(String imageName) {
        assert imageName, "Image name is required"

        String returnValue = exec.params(
                returnStdout: true,
                script: "docker images -q ${imageName}",
                basedir: TMP_DIR)

        return !returnValue.isEmpty()
    }

    String build(File dockerFile, String imageName) {
        assert dockerFile, 'Dockerfile is required'
        assert dockerFile.exists(), 'Dockerfile must be present on filesystem'

        return exec.params(
                returnStatus: true,
                script: "docker build -t ${imageName} ./",
                basedir: dockerFile.parent) == "0"
    }
}
