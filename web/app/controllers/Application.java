package controllers;

import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Application extends Controller {

    public Result index() {
        ObjectNode result = Json.newObject();
        result.put("exampleField1", "foobar");
        result.put("exampleField2", "Hello world!");
        return ok(result);
    }

}
