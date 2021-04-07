# ![TiteCan](https://github.com/peasoupio/inv/blob/master/core/src/main/resources/public/favicon-32x32.png) INV Repository - Public Intertwined network valuables artefacts

**inv-repo** is a repository of public implementations usable by ***INV***.  
INV is located at https://github.com/peasoupio/inv.  

## How to use  
  
### With a `href` file (for ***Composer***):
An `href` file indicate to ***Composer*** to reference an remote REPO file.  
The reference has to be reachable with the HTTP(s) protocol.  
An example for the file `.../hrefs/public.net.http.href`:
`https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/net/http/repo.yml`

### With a `repo` statement:
A `repo` statement can be found in a `INV` file.   
An example:  
`repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/net/http/repo.yml"`
>**IMPORTANT**: It creates a strong relationship with this repository.

### With a CLI command:
This is used mainly to inspect the content of a REPO manually.  
An example:  
`inv repo get -r https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/net/http/repo.yml`

## Available implementations
```
io/
    files/
    templates/
    logger/
net/
    http/     
    ssh/  
os/
    exec/  
    info/
tools/
    maven/
    docker/
    kubernetes/
    
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

