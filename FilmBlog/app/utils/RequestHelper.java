package utils;

import play.mvc.Http;

/**
 * Created by boris on 11/19/15.
 */
public class RequestHelper {

    public static boolean isAjax() {
        Http.Request request = Http.Context.current().request();
        if (request.getHeader("X-Requested-With") != null) {
            return true;
        }
        return false;
    }
}
