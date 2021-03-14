import groovy.transform.Field
@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*

@Field
def app1 = new File("./resources/test/SimpleMavenLookup/app1").absolutePath

@Field
def app2 = new File("./resources/test/SimpleMavenLookup/app2").absolutePath

@Test
void mavenSimpleLookup() {

    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "app1"
            path app1

            // Using default
            require { Maven }

            step {
                assertNotNull $maven.hasProperty("poms")
            }
        }

        addInvBody {
            name "app2"
            path app2

            require { Maven } using {

                // Disabling defaults and calling manually
                defaults false

                resolved {
                    analyze(app2)
                }
            }
        }
    }
    assertTrue isOk
}

@Test
void mavenSimpleLookup_withoutDefaults() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "app1"
            path app1

            // Using default
            require { Maven } using {
                defaults false
            }

            step {
                assertNull $maven.hasProperty("poms")
            }
        }
    }

    assertTrue isOk
}
