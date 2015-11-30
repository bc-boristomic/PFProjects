package utils;

/**
 * Created by boris on 11/29/15.
 */
public class Validations {

    /**
     * Checks every input if contains any characters
     *
     * @param string
     * @return
     */
    public static boolean isStringEmpty(String string) {
        if ("".equals(string.trim())) {
            return true;
        }
        return false;
    }

}
