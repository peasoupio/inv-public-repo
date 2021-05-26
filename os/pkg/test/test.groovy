@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*

@Test
void test_name() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { PackageManager } using {
                resolved {
                    def pkgManager = response
                    assertNotNull(pkgManager)
                    assertNotNull(pkgManager.name)
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_install_uninstall() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { PackageManager } using {
                resolved {
                    def pkgManager = response
                    assertTrue pkgManager.install("notepadplusplus.install")

                    if (pkgManager.verify())
                        assertTrue pkgManager.uninstall("notepadplusplus.install")
                }
            }

        }
    }

    assertTrue isOk
}