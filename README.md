# ![TiteCan](https://github.com/peasoupio/inv/blob/master/src/main/resources/public/favicon-32x32.png) INV Repository - Public Intertwined network valuables artefacts

**inv-repo** is a public repository for public artefacts used with INV.  
INV main repository is [located here](https://github.com/peasoupio/inv).  

## How to use  
1. Create a REPO file  
1. Add this repository as its source  
1. Define the `entry` option with the required INV files  

Here's an example: 
```groovy
repo {
    name "inv-repo"
    src "https://github.com/peasoupio/inv-repo.git"
    entry '''
io/files
net/http
net/ssh
tools/maven
'''

    init "git clone ${src} ."
}
```

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
        inv.groovy
        resources/
        testResources/
tools/
    {tool's name}/
        inv.groovy
        resources/
        testResources/
```
> `.groovy` and `.yaml` are both supported

