get "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/files/repo.yml"

inv {
    name "template"

    require { Files }

    broadcast { PropertiesTemplate } using {
        ready { return new PropertiesHelper({ debug(it) }) }
    }
}