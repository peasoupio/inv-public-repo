inv {
    name "info"

    broadcast { OSCurrentInfo } using {
        ready { return new OSInfo() }
    }
}