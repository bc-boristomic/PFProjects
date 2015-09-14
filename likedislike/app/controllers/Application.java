package controllers;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }

    public Result getLike() {
        DynamicForm form = Form.form().bindFromRequest();

        String s = form.data().get("pressed");
        String address = form.data().get("address");

        Logger.info(s + " " + address);


        return ok(s);
    }

}
