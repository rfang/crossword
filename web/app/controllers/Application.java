package controllers;

import models.Crossword;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public Result index(String url) {
        if (Crossword.find.where().eq("url", url).findList().size() == 0) {
            Crossword crossword = new Crossword(url);
            crossword.save();
        }
        return ok(Json.toJson(Crossword.find.all()));
    }

}
