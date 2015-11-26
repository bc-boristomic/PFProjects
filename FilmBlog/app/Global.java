import models.User;
import play.Application;
import play.GlobalSettings;
import utils.FillDatabase;

/**
 * Created by boris on 11/21/15.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {
        if (User.tableIsEmpty()) {
            FillDatabase.createRoles();
            FillDatabase.createUsers();
        }
    }
}
