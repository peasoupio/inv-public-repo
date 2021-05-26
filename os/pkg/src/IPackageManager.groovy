interface IPackageManager {

    String getName()
    boolean isAvailable()
    String getVersion()

    boolean verify()
    boolean install(String packageName)
    boolean uninstall(String packageName)
    boolean update(String packageName)

}