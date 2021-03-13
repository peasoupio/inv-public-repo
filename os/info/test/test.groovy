@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*

@Test
void get_detectedOS() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { OSCurrentInfo }

            step {
                assertNotNull $oSCurrentInfo
                assertNotNull $oSCurrentInfo.detectedOS
            }
        }
    }

    assertTrue isOk
}
