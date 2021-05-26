repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/logger/repo.yml"
repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/info/repo.yml"
repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/os/exec/repo.yml"

inv {
    name "os.pkg"

    require { Logger }

    require { OSCurrentInfo }
    require { Exec }

    step {

        // Get the current OS package manager
        IPackageManager pkgManager
        switch ($oSCurrentInfo.detectedOS) {
            case $OSCurrentInfo.Windows:
                pkgManager = new Chocolatey(exec: $exec as $Exec, logger: $logger as $Logger)
                break
            case $OSCurrentInfo.Linux:
                pkgManager = new Apt()
                break
            case $OSCurrentInfo.MacOS:
                pkgManager = new Brew()
                break
            case $OSCurrentInfo.Other:
            default:
                pkgManager = null
        }

        // If PackageManager is not available, do not process anything
        if (!pkgManager) {
            $logger.error """No package manager detected for your operating system."""
            return
        }

        if (!pkgManager.verify()) {
            $logger.warn """Cannot use package manager ${pkgManager.name} properly. 
Check if you have the sufficient privileges to use it or if it's installed."""

            broadcast { PackageManager } using {
                delayed true
                ready { pkgManager }
            }
        }
    }
}