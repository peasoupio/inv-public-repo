repo "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/files/repo.yml"

inv {
    markdown '''
Provide a Maven reader that scan the callee current location for 'pom.xml' files.  
It also analyzes the artifacts and the dependencies to generate broadcasts (artifacts) and requires (dependencies).
'''

    require { Files }

    broadcast { Maven } using {
        markdown '''
Returns a new Maven instance.  

Exposes:
```
    $: Default hook so it can analyze each callee individually. It uses the callee 'pwd' location.
    analyze: Analyze the artifacts and dependencies for a specific folder.
```
'''
        dynamic { new SimpleMavenHandler(files: $files as $Files).analyze(caller.getPath()) }
    }
}