import java.nio.file.Files
import java.nio.file.Path

class Chocolatey implements IPackageManager {

     $Exec exec
     $Logger logger

     private String version
     private boolean available

     /**
      * Check Chocolatey current installation, if present.
      * @return This.
      */
     @Override
     boolean verify() {
          getInstallInfo()

          // Check if available and able to install packages
          return this.available && canInstall()
     }

     @Override
     String getName() {
          return "chocolatey"
     }

     @Override
     boolean isAvailable() {
          return this.available
     }

     @Override
     String getVersion() {
          return this.version
     }

     @Override
     boolean install(String packageName) {
          assert packageName

          def exitCode = exec.params(command: "choco install ${packageName} -ry", returnStatus: true, includeSystemProperties: true)
          if (exitCode == 0) {
               logger.info("${packageName} installed.")
               return true
          } else {
               logger.error("Could not install ${packageName}")
               return false
          }
     }

     @Override
     boolean uninstall(String packageName) {
          assert packageName

          def exitCode = exec.params(command: "choco uninstall ${packageName} -ry", returnStatus: true, includeSystemProperties: true)
          if (exitCode == 0) {
               logger.info("${packageName} installed.")
               return true
          } else {
               logger.error("Could not install ${packageName}")
               return false
          }
     }

     @Override
     boolean update(String packageName) {
          assert packageName

          def exitCode = exec.params(command: "choco upgrade ${packageName} -ry", returnStatus: true, includeSystemProperties: true)
          if (exitCode == 0) {
               logger.info("${packageName} installed.")
               return true
          } else {
               logger.error("Could not uninstall ${packageName}")
               return false
          }
     }

     private void getInstallInfo() {
          this.version = exec.params(command: "choco -v", returnStdout: true, includeSystemProperties: true)
          this.available = (this.version)
     }

     /**
      * Check whether or not the current process has enough privileges to install any package
      * @return True if able to install (or upgrade, uninstall, etc.), otherwise false.
      */
     private boolean canInstall() {
          return Files.isWritable(Path.of("C:\\ProgramData\\chocolatey\\lib"))
     }
}
