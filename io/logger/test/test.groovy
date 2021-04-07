@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*


@Test
void test_info() {

    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {

            name "test_info"

            require { Logger }

            step {
                $logger.info("Message1")
            }
        }
    }
    assertTrue isOk
}

@Test
void test_warn() {

    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {

            name "test_warn"

            require { Logger }

            step {
                $logger.warn("Message2")
            }
        }
    }
    assertTrue isOk
}

@Test
void test_error() {

    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {

            name "test_error"

            require { Logger }

            step {
                $logger.error("Message3")
            }
        }
    }
    assertTrue isOk
}