// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/caglasevencan/cs151/lab13/conf/routes
// @DATE:Tue Mar 19 15:59:07 PDT 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def select: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.select",
      """
        function(question0,answer1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "select/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("question", question0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("answer", answer1))})
        }
      """
    )
  
    // @LINE:17
    def view: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.view",
      """
        function(question0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "view/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("question", question0)) + "/"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
