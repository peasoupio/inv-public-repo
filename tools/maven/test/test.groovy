@Test
void mavenSimpleLookup() {
    def app1 = new File("./testResources/SimpleMavenLookup/app1").absolutePath
    def app2 = new File("./testResources/SimpleMavenLookup/app2").absolutePath

    simulate(
            "inv.groovy",
            "../../io/files/inv.groovy",
            {
                name "app1"
                path app1

                // Using default
                require { Maven } into '$maven'

                step {
                    assert $maven.poms
                }
            },
            {
                name "app2"

                require { Maven } using {

                    // Disabling defaults and calling manually
                    defaults false

                    resolved {
                        response.analyze(app2)
                    }
                }
            }
    )
    assertTrue isOk
}
