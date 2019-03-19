package controllers;
import java.util.HashMap;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private static HashMap<String, HashMap<String, Integer>> results = new HashMap<>();

    public Result select(String problem, String choice) {

        if (results.get(problem) == null)
            results.put(problem, new HashMap<>());
        if (results.get(problem).get(choice) == null)
            results.get(problem).put(choice,  0);
        results.get(problem).put(choice, results.get(problem).get(choice) + 1);


        return ok("You selected " + choice + " for " + problem + "\n").as("text/plain");
    }
    public Result view(String problem)
    {
        return ok("" + results.get(problem));
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

}
