package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Crossword;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class Application extends Controller {

    public Result list() {
        Map<Long, String> ids = new HashMap<>();
        for (Crossword crossword : Crossword.find.all()) {
            ids.put(crossword.id, crossword.name);
        }
        return ok(Json.toJson(ids));
    }

    public Result create() {
        JsonNode json = request().body().asJson();
        String name = "";
        String url = "https://github.com/alexdej/puzpy/raw/master/testfiles/nyt_partlyfilled.puz";
        if (false && json == null) {
            return badRequest("Expecting Json data");
        } else {
            if (json != null) {
                name = json.findPath("name").textValue();
                url = json.findPath("url").textValue();
            }
            // TODO: remove defaults
            if (name == null) {
                name = "";
            }
            if (url == null) {
                url = "https://github.com/alexdej/puzpy/raw/master/testfiles/nyt_partlyfilled.puz";
            }
            if (name == null && url == null) {
                return badRequest("Missing parameter [name, url]");
            } else if (name == null) {
                return badRequest("Missing parameter [name]");
            } else if (url == null) {
                return badRequest("Missing parameter [url]");
            } else {
                Crossword crossword = new Crossword(name, url);
                crossword.save();
                return ok();
            }
        }
    }

    public Result show(long id) {
        return ok(Json.toJson(Crossword.find.byId(id)));
    }

    public Result show_fill(long id) {
        return ok(Json.toJson(Crossword.find.byId(id).fill));
    }

    public Result update_fill(long id) {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            String fill = json.findPath("fill").textValue();
            if (fill == null) {
                return badRequest("Missing parameter [fill]");
            } else {
                Crossword crossword = Crossword.find.byId(id);
                crossword.fill = fill;
                crossword.save();
                return ok();
            }
        }
    }

}
