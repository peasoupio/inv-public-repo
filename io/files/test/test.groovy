@Test
void glob() {

    def files = new File("resources/test/files").absolutePath
    def testResults = []

    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {
            require { Files } into '$files'

            step {
                $files.glob(files).each { testResults << new File(it).name + "-GLOB-ALL" }
                $files.glob(files, "*file*").each { testResults << new File(it).name + "-GLOB-PATTERN" }
                $files.glob(files, "*file*", "*file2*").each { testResults << new File(it).name + "-GLOB-EXCLUDE" }
            }
        }
    }
    assertTrue isOk

    // GLOB All
    assert testResults.contains("file1-GLOB-ALL")
    assert testResults.contains("file2-GLOB-ALL")

    // GLOB Pattern
    assert testResults.contains("file1-GLOB-ALL")
    assert testResults.contains("file2-GLOB-ALL")

    // GLOB Exclude
    assert testResults.contains("file1-GLOB-EXCLUDE")
    assert !testResults.contains("file2-GLOB-EXCLUDE")
}

@Test
void find() {

    def files = new File("resources/test/files").absolutePath
    def testResults = []

    simulate {
        addInvFile "vars/inv.groovy"
        addInvBody {
            require { Files } into '$files'

            step {
                $files.find(files as String).each { testResults << it.name + "-FIND-ALL" }
                $files.find(files, "file").each { testResults << it.name + "-FIND-PATTERN" }
                $files.find(files, "file", "file2").each { testResults << it.name + "-FIND-EXCLUDE" }
            }
        }
    }

    assertTrue isOk

    // Find All
    assert testResults.contains("file1-FIND-ALL")
    assert testResults.contains("file2-FIND-ALL")

    // Find Pattern
    assert testResults.contains("file1-FIND-PATTERN")
    assert testResults.contains("file2-FIND-PATTERN")

    // Find Exclude
    assert testResults.contains("file1-FIND-EXCLUDE")
    assert !testResults.contains("file2-FIND-EXCLUDE")
}