@Test
void get() {
    simulate(
            "inv.groovy",
            {
                require { HTTP } into '$http'

                step {
                    assert $http.newRequest("https://google.com")
                            .send()
                            .valid()
                }
            }
    )
    assertTrue isOk
}

@Test
void post() {
    simulate(
            "inv.groovy",
            {
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
    )
    assertTrue isOk
}