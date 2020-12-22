@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*

@Test
void get() {
    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {
            require { HTTP } into '$http'

            step {
                assert $http.newRequest("https://google.com")
                        .send()
                        .valid()
            }
        }
    }

    assertTrue isOk
}

@Test
void post() {
    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {
            require { HTTP } into '$http'

            step {

                def data = "My super duper hyper mega data"

                def req = $http.newRequest("https://postman-echo.com/post")
                        .method("POST")
                        .parameter("value1", data)
                        .send()

                assert req.valid()
                assert req.toText()
                assert req.toJson().form.value1 == data
            }
        }
    }
    assertTrue isOk
}