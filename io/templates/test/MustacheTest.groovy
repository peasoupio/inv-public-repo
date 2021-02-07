@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*


@Test
void ok() {

    def mustacheTestFile = new File("resources/test/mustache.txt")
    def outputTestFile = new File("resources/test/mustache.txt.output")
    outputTestFile.delete()

    def missingCodes = []

    simulate {
        addInvFile "vars/mustache.groovy"
        addInvBody {
            require { MustacheTemplate }

            step {
                missingCodes = $mustacheTemplate.missing(mustacheTestFile)

                $mustacheTemplate.parse(mustacheTestFile, [value: "super value"], outputTestFile)


            }
        }
    }
    assertTrue isOk

    assertTrue missingCodes.contains("one")

    assertEquals """My super value is printed.
This  is not.""", outputTestFile.text

    outputTestFile.delete()
}