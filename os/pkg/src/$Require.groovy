/**
 * From https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/logger/repo.yml
 */
interface $Logger {
    void info(String message)
    void warn(String message)
    void error(String message)
}

/**
 * From https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/info/repo.yml
 */
interface $OSCurrentInfo {
    static String Windows = "Windows"
    static String MacOS = "MacOS"
    static String Linux = "Linux"
    static String Other = "Other"

    String getDetectedOS()
}

/**
 * From https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/exec/repo.yml
 */
interface $Exec {
    boolean simple(String command)
    Object params(Map args)
}