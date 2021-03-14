import org.apache.maven.model.Dependency
import org.apache.maven.model.Model
import org.apache.maven.model.io.xpp3.MavenXpp3Reader

class SimpleMavenHandler {

    $Files files

    Map $default() {
        this.analyze(getPath())
    }

    Map analyze(String pwd, String exclude = "") {

        def poms = []

        // Using find makes it faster
        for (File file : (files as $Files).find(pwd, "pom.xml", exclude)) {

            MavenXpp3Reader reader = new MavenXpp3Reader()
            Model model = reader.read(new FileReader(file))

            broadcast { Artifact } using {
                id model.groupId + ":" + model.artifactId

                ready { [model: model] }
            }

            for (Dependency dep : model.dependencies) {
                require { Artifact(dep.groupId + ":" + dep.artifactId) }
            }

            poms << model
        }

        return [
                poms: poms
        ]
    }
}
