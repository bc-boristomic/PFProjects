package utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by boris on 11/19/15.
 */
public class Hash {

    public static String createHashedPassword(String plainText) {
        if (plainText == null) {
            throw new IllegalArgumentException("Inputed string empty!");
        }
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainText, String hashed) {
        if (plainText == null || hashed == null) {
            return false;
        }
        return BCrypt.checkpw(plainText, hashed);
    }

}
