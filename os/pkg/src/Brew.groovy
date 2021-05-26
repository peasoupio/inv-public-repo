class Brew implements IPackageManager {

     final String name = "brew"

     @Override
     boolean isAvailable() {
          return false
     }

     @Override
     String getVersion() {
          return null
     }

     @Override
     boolean verify() {
          return false
     }

     @Override
     boolean install(String packageName) {

     }

     @Override
     boolean uninstall(String packageName) {

     }

     @Override
     boolean update(String packageName) {

     }
}
