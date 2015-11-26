package utils;

import controllers.routes;
import models.Role;
import models.User;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import resources.FlashMessages;

import static play.mvc.Controller.flash;

/**
 * Created by boris on 11/21/15.
 */
public class Authorization {

    public static class AdminFilter extends Security.Authenticator {

        @Override
        public String getUsername(Http.Context context) {
            if (!context.session().containsKey(Constants.EMAIL)) {
                return null;
            }
            User user = User.findByEmail(context.session().get(Constants.EMAIL));
            if (user != null && user.getRoles() != null && user.getRoles().size() > 0) {
                for (Role role : user.getRoles()) {
                    if (Constants.ROLE_ADMIN.equals(role.getName())) {
                        return user.getEmail();
                    }
                }
            }
            return null;
        }

        @Override
        public Result onUnauthorized(Http.Context context) {
            flash("warning", FlashMessages.ACCESS_UNAUTHORIZED);
            return redirect(routes.ApplicationController.index());
        }

    }
}
