import groovy.json.JsonSlurper

class LocalKubernetesHandler {

    private final static File TMP_DIR = new File(System.getProperty("java.io.tmpdir"))

    $Exec exec

    String apply(File yaml) {
        assert yaml, 'Yaml is required'
        assert yaml.exists(), 'Yaml must be present on filesystem'

        return exec.params(
                returnStatus: true,
                delegateEnvVars: true,
                script: "kubectl apply -f ${yaml.name}",
                basedir: yaml.parent) == "0"
    }

    String deleteNow(String resourceName) {
        assert resourceName, "ResourceName is required"

        return exec.params(
                returnStatus: true,
                delegateEnvVars: true,
                script: "kubectl delete ${resourceName} --grace-period=0 --force",
                basedir: TMP_DIR) == "0"
    }

    String ipOf(String serviceName) {
        assert serviceName, 'ServiceName is required'

        String response = exec.params(
                returnStdout: true,
                delegateEnvVars: true,
                script: "kubectl get ep ${serviceName} --output=json",
                basedir: TMP_DIR)

        def endpoints = new JsonSlurper().parseText(response) as Map

        if (endpoints?.subsets?[0]?.addresses)
            return endpoints.subsets[0].addresses[0].ip

        if (endpoints?.subsets?[0]?.notReadyAddresses)
            return endpoints.subsets[0].notReadyAddresses[0].ip

        return '127.0.0.1'
    }

    String proxyOf(String serviceName, String protocol = "http", Integer port = 80) {
        assert serviceName, 'Service name is requjred'
        assert protocol, "Protocol is required. By default 'http'"
        assert port, "Port is required. By default '80'"

        def doesServiceExists =  exec.params(
                returnStatus: true,
                delegateEnvVars: true,
                script: "kubectl describe service ${serviceName}",
                basedir: TMP_DIR) == "0"

        // Check kubernetes service exists
        if (!doesServiceExists)
            return null

        return "http://localhost:8001/api/v1/namespaces/default/${serviceName}/${protocol}:${serviceName}:${port}/proxy/"
    }
}
