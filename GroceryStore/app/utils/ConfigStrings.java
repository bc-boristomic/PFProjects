package utils;

import play.Play;

/**
 * Created by boris on 12/1/15.
 */
public class ConfigStrings {

    public static final String CLOUDINARY_STRING = Play.application().configuration().getString("CLOUDINARY_URL");

}
