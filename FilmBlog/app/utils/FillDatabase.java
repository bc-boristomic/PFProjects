package utils;

import models.Role;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by boris on 11/21/15.
 */
public class FillDatabase {

    public static final Role ADMIN_ROLE = new Role(Constants.ROLE_ADMIN);
    public static final Role WRITER_ROLE = new Role(Constants.ROLE_WRITER);
    public static final Role COMMENTER_ROLE = new Role(Constants.ROLE_COMMENTER);

    public static void createRoles() {
        ADMIN_ROLE.save();
        WRITER_ROLE.save();
        COMMENTER_ROLE.save();
    }

    public static void createUsers() {
        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setFirstName("Boris");
        admin.setLastName("Tomic");
        admin.setPassword("Pass1@");
        admin.setRoles(new ArrayList<Role>(Arrays.asList(new Role[] {ADMIN_ROLE, WRITER_ROLE})));
        admin.save();
    }
}
