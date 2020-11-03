# ![TiteCan](https://github.com/peasoupio/inv/blob/master/src/main/resources/public/favicon-32x32.png) INV Repository - Public Intertwined network valuables artefacts

**inv-repo** is a public repository for public artefacts used with INV.  
INV main repository is [located here](https://github.com/peasoupio/inv).  

## How to use  
Use INV Repo command.  
Per example: `inv repo get -r https://raw.githubusercontent.com/peasoupio/inv-repo/master/net/http/scm.groovy`  
  
## Available entries
```
io/
    files/   # Filesystem I/O
net/
    http/     
    ssh/    
tools/
    maven/
    
```

## How to contribute
Here's a scemantic representation of the targeted file structure:
```
{physical device}/
    {protocol or structure}/
        src/
            // Groovy class files, p.e "MyClass.groovy"
        test/
            // Groovy test class files, p.e "MyTest.groovy"
        vars/
            // INV files, p.e "inv.groovy"
        resources/
            main/
                // Any type of files
            test/
                // Any type of test files
        
        scm.groovy (or .yml, .yaml)
          
tools/
    {tool's name}/
        src/
            // Groovy class files, p.e "MyClass.groovy"
        test/
            // Groovy test class files, p.e "MyTest.groovy"
        vars/
            // INV files, p.e "inv.groovy"
        resources/
            main/
                // Any type of files
            test/
                // Any type of test files
        
        scm.groovy (or .yml, .yaml)
```
> `.groovy` and `.yaml` are both supported

