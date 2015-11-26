package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.Authorization;
import views.html.*;

/**
 * Created by boris on 11/19/15.
 */
public class PostController extends Controller {

    @Security.Authenticated(Authorization.AdminFilter.class)
    public Result singlePost() {
        return ok(post.render());
    }
}
