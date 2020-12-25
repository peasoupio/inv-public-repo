@Grab("org.apache.commons:commons-text:1.8")
@Grab('com.github.spullara.mustache.java:compiler:0.9.6')
import java.lang.Object

get "https://raw.githubusercontent.com/peasoupio/inv-public-repo/master/io/files/repo.yml"

inv {
    name "mustacheTemplate"

    require { Files }

    broadcast { MustacheTemplate } using {
        ready { return new MustacheHelper($files, { debug(it) }) }
    }
}

