// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/caglasevencan/cs151/lab13/conf/routes
// @DATE:Tue Mar 19 15:59:07 PDT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
