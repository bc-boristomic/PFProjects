package utils;

import models.User;
import play.mvc.Http;

/**
 * Created by boris on 11/19/15.
 */
public class SessionHelper {

    public static User getCurrentUser(Http.Context context) {
        String email = context.session().get("email");
        if (email == null) {
            return null;
        }
        User user = User.findByEmail(email);
        if (user == null) {
            return null;
        }
        return user;
    }

}
