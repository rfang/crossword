package controllers;

import app.Puzzle;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    public Result index() {
        Puzzle puzzle = new Puzzle();
        Map<String, Object> map = new HashMap<>();
        map.put("line", puzzle.line);
        map.put("width", puzzle.width);
        map.put("height", puzzle.height);
        map.put("solution", puzzle.solution);
        map.put("fill", puzzle.fill);
        map.put("clues", puzzle.clues);
        return ok(Json.toJson(map));
    }

}
