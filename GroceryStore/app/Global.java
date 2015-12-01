import com.cloudinary.Cloudinary;
import models.Article;
import models.Category;
import models.Image;
import play.Application;
import play.GlobalSettings;
import play.Play;
import utils.ConfigStrings;

import java.util.UUID;

/**
 * Created by boris on 11/29/15.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {

        Image.cloudinary = new Cloudinary(ConfigStrings.CLOUDINARY_STRING);

        if (Article.getFinder().findRowCount() == 0) {
            for (int i = 0; i < 10; i++) {
                Article article = new Article();
                if (i == 0) {
                    article.setName("Carrot");
                } else if (i == 1) {
                    article.setName("Apple");
                } else if (i == 2) {
                    article.setName("Orange");
                } else {
                    article.setName("Generated article " + i);
                }
                article.setSku(UUID.randomUUID().toString());
                article.setDescription("Article description");
                if (i > 2 && i % 2 == 0) {
                    article.setAvailability(true);
                } else if (i > 2 && i % 2 != 0) {
                    article.setAvailability(false);
                } else {
                    article.setAvailability(true);
                }
                article.save();
            }
        }

        if (Category.getFinder().findRowCount() == 0) {
            Category category;
            for (int i = 0; i < 5; i++) {
                if (i == 0){
                    category = new Category("Food", true);
                } else if (i == 1) {
                    category = new Category("Fruits", true);
                } else if ( i == 2) {
                    category = new Category("Vegetables", true);
                } else {
                    category = new Category("Generated category " + i, false);
                }
                category.save();
            }
        }
    }
}
