package utils;

import com.typesafe.config.ConfigFactory;
import play.Play;

/**
 * Created by boris on 12/1/15.
 */
public class ResourceStrings {

    private static final String VALIDATION_MESSAGES = "resources/validation.messages.properties";


    public static final String ARTICLE_NAME = ConfigFactory.load(VALIDATION_MESSAGES).getString("article.name");
    public static final String ARTICLE_SKU = ConfigFactory.load(VALIDATION_MESSAGES).getString("article.sku");
    public static final String ARTICLE_DESCRIPTION = ConfigFactory.load(VALIDATION_MESSAGES).getString("article.description");

    public static final String CLOUDINARY_STRING = Play.application().configuration().getString("CLOUDINARY_URL");

}
