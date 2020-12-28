get "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/files/repo.yml"

inv {
    name "mustacheTemplate"

    broadcast { MustacheTemplate } using {
        ready { return new MustacheHelper({ debug(it) }) }
    }
}

