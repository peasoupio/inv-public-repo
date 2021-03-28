@groovy.transform.BaseScript(io.peasoup.inv.testing.JunitScriptBase.class)
import org.junit.Test

import static org.junit.Assert.*

@Test
void test_simple_ok() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertTrue execHandler.simple("ping 127.0.0.1")
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_simple_fail() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertFalse execHandler.simple("ping 999.999.999.999")
                }
            }

        }
    }

    assertTrue isOk
}


@Test
void test_params_command_ok() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertTrue execHandler.params(command: "ping 127.0.0.1")
                }
            }

        }
    }

    assertTrue isOk
}
@Test
void test_params_command_fail() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertFalse execHandler.params(command: "ping 999.999.999.999")
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_params_basedir() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    def here = new File("").absoluteFile.name
                    def pwd = execHandler.params(command: "pwd", returnStdout: true).toString()
                    pwd = pwd.substring(pwd.lastIndexOf("/") + 1)
                    assertEquals(here, pwd)

                    def there = new File("").absoluteFile.parentFile.name
                    pwd = execHandler.params(command: "pwd", basedir: "../", returnStdout: true).toString()
                    pwd = pwd.substring(pwd.lastIndexOf("/") + 1)
                    assertEquals(there, pwd)
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_params_returnStdOut() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertNotNull execHandler.params(command: "ping 127.0.0.1", returnStdout: true)
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_params_returnValue() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertEquals 0, execHandler.params(command: "ping 127.0.0.1", returnStatus: true)
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_params_timeoutMs() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    assertFalse execHandler.params(command: "sleep 5", timeoutMs: 1000) // 1 second timeout
                }
            }

        }
    }

    assertTrue isOk
}

@Test
void test_params_includeSystemProperties() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    // Will not find "set" program
                    execHandler.params(command: "set", includeSystemProperties: false, returnStdout: true)
                }
            }

        }
    }

    assertFalse isOk
}

@Test
void test_params_fireAndForget() {
    simulate {
        addInvFile "vars/inv.groovy"

        addInvBody {
            name "inv1"

            // Using default
            require { Exec } using {
                resolved {
                    def execHandler = response as $ExecHandler
                    assertNotNull(execHandler)

                    // Always null since we can't inspect actual comand results
                    assertNull execHandler.params(command: "ping 127.0.0.1", fireAndForget: true)
                }
            }

        }
    }

    assertTrue isOk
}




interface  $ExecHandler {
    Boolean simple(String command)

    /**
     * Execute a process using a set of options.
     * Available options:
     * - String command
     * - String basedir = "."
     * - boolean returnStdout = false
     * - boolean returnStatus = false
     * - int timeoutMs = DEFAULT_TIMEOUT_MS
     * - List vars = []
     * - boolean includeSystemProperties = false
     * - boolean fireAndForget = false

     * @param opts
     * @return
     */
    Object params(Map opts)
}