package controllers;

import models.User;
import play.mvc.Controller;

import play.mvc.Result;
import views.html.*;

import java.util.List;

public class ApplicationController extends Controller {

    public Result index() {

        List<User> users = User.getFinder().all();

        return ok(index.render(users));
    }

    public Result aboutMe() {
        return ok(about.render());
    }

    public Result contactMe() {
        return ok(contact.render());
    }

}
