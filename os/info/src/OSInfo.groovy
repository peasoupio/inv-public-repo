class OSInfo {

    private OSType detectedOS

    OSInfo() {
        this.detectedOS = detectOperatingSystemType()
    }

    String getDetectedOS() {
        return detectedOS.toString()
    }

    /**
     * detect the operating system from the os.name System property and cache
     * the result
     *
     * @returns - the operating system detected
     */
    static OSType detectOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            return OSType.MacOS
        } else if (OS.indexOf("win") >= 0) {
            return OSType.Windows
        } else if (OS.indexOf("nux") >= 0) {
            return OSType.Linux
        } else {
            return OSType.Other
        }
    }
}
