@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*

@Test
void mavenSimpleLookup() {
    def app1 = new File("./resources/test/SimpleMavenLookup/app1").absolutePath
    def app2 = new File("./resources/test/SimpleMavenLookup/app2").absolutePath

    simulate {
        addRepoFile "../../io/files"
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "app1"
            path app1

            // Using default
            require { Maven } into '$maven'

            step {
                assert $maven.poms
            }
        }

        addInvBody {
            name "app2"
            path app2

            require { Maven } using {

                // Disabling defaults and calling manually
                defaults false

                resolved {
                    response.analyze(app2)
                }
            }
        }
    }
    assertTrue isOk
}
