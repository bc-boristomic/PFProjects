import com.cloudinary.Cloudinary;
import models.Image;
import play.Application;
import play.GlobalSettings;
import utils.FillDatabase;
import utils.ResourceStrings;

/**
 * Created by boris on 11/29/15.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {

        Image.cloudinary = new Cloudinary(ResourceStrings.CLOUDINARY_STRING);

        FillDatabase.createArticles();
        FillDatabase.createCategories();

    }
}
