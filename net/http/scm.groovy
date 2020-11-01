repo {
    name "io.peasoup.net.http"

    path "net/http"
    src "https://github.com/peasoupio/inv-repo.git"

    hooks {

        init """
git clone -b feature/0.1-beta ${src} .
    """

        pull """
git pull
    """
    }
}