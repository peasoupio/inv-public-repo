inv {

    markdown '''
Files (I/O) general tool.   
it is more performant than FileNameFinder provided by groovy itself.
'''

    broadcast { Files } using {
        markdown '''
A default implementation for files interactions.    

Methods:
```
    $files.glob: Suitable for specific files using Ant style filtering
    $files.find: Suitable for performances on generic file patterns
```
'''
        ready { new FilesHandler() }
    }
}