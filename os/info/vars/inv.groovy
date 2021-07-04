inv {
    name "info"

    broadcast { OSCurrentInfo } using {
        global { return new OSInfo() }
    }
}