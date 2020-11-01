inv {
    markdown '''
Provide easy-to-use HTTP request methods.
'''

    broadcast $inv.HTTP using {
        markdown '''
Returns a new RequestHandler.  
Methods:
```
    $http.newRequest: Create the default HTTP request (simple)
    $http.newSimpleRequest: Create a simple request.
```
'''
        ready { return new RequestHandler() }
    }
}

