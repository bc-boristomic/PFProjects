package utils;

import java.util.regex.Pattern;

/**
 * Created by boris on 11/20/15.
 */
public class Patterns {

    public static final String EMAIL = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    public static final String PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

}
