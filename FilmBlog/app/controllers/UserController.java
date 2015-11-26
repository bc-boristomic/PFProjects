package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import resources.FlashMessages;
import utils.Constants;
import viewmodels.UserLoginVM;
import viewmodels.UserRegistrationVM;
import views.html.user.*;


/**
 * Created by boris on 11/19/15.
 */
public class UserController extends Controller {

    private static final Form<UserRegistrationVM> REGISTER_FORM = Form.form(UserRegistrationVM.class);
    private static final Form<UserLoginVM> LOGIN_FORM = Form.form(UserLoginVM.class);


    public Result login() {
        return ok(login.render(LOGIN_FORM));
    }

    public Result loginUser() {
        Form<UserLoginVM> submitted = LOGIN_FORM.bindFromRequest();

        if (submitted.hasErrors()) {
            return badRequest(login.render(submitted));
        }

        UserLoginVM user = submitted.get();
        User temp = User.getUserForLogin(user);

        if (temp != null) {
            if (submitted.field("remember").value() != null) {
                createCookie(temp);
            }
            createSession(temp);
            flash("success", FlashMessages.LOGIN_SUCCESS);
            return redirect(routes.ApplicationController.index());
        }
        flash("error", FlashMessages.LOGIN_FAIL);
        return login();
    }

    private void createSession(User user) {
        session().clear();
        session().put(Constants.EMAIL, user.getEmail());
    }

    private void createCookie(User user) {
        response().discardCookie(Constants.EMAIL);
        response().setCookie(Constants.EMAIL, user.getEmail());
    }

    /**
     * Renders registration view with form for registration.
     * Form is created from Viewmodel class UserRegistrationVM.
     *
     * @return ok and renders register html with REGISTER_FORM
     */
    public Result registration() {
        return ok(register.render(REGISTER_FORM));
    }

    /**
     * Registers user to website and saves information to database.
     *
     * @return
     */
    public Result registerUser() {
        Form<UserRegistrationVM> submitted = REGISTER_FORM.bindFromRequest();

        if (submitted.hasErrors()) {
            return badRequest(register.render(submitted));
        }

        User temp = User.createNewUser(submitted.get());
        if (temp != null) {
            createSession(temp);
            flash("success", FlashMessages.REGISTRATION_SUCCESS);
            return redirect(routes.ApplicationController.index());
        }
        flash("error", FlashMessages.REGISTRATION_FAIL);
        return registration();
    }

    public Result logout() {
        session().clear();
        response().discardCookie(Constants.EMAIL);
        return redirect(routes.ApplicationController.index());
    }

    public Result checkEmailRegistration() {
        DynamicForm form = Form.form().bindFromRequest();
        String email = form.field("value").value();
        if (User.checkEmailIfExists(email)) {
            return badRequest();
        }
        return ok();
    }

    public Result checkEmailLogin() {
        DynamicForm form = Form.form().bindFromRequest();
        String email = form.field("value").value();
        if (User.checkEmailIfExists(email)) {
            return ok();
        }
        return badRequest();
    }
}
