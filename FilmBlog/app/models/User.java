package models;

import com.avaje.ebean.Model;
import org.joda.time.DateTime;
import play.Logger;
import play.data.validation.Constraints;
import resources.ValidationMessages;
import utils.Constants;
import utils.Hash;
import utils.Patterns;
import viewmodels.UserLoginVM;
import viewmodels.UserRegistrationVM;

import javax.persistence.*;
import java.util.List;

/**
 * Created by boris on 11/18/15.
 */
@Entity
@Table(name = "app_user")
public final class User extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;
    @Constraints.Required(message = ValidationMessages.EMAIL_REQUIRED)
    @Constraints.Pattern(value = Patterns.EMAIL, message = ValidationMessages.EMAIL_PATTERN)
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Constraints.Required(message = ValidationMessages.PASSWORD_REQUIRED)
    @Constraints.Pattern(value = Patterns.PASSWORD, message = ValidationMessages.PASSWORD_PATTERN)
    @Column(name = "password", nullable = false)
    private String password;
    @Constraints.MinLength(value = 3, message = ValidationMessages.NAME_MIN)
    @Constraints.MaxLength(value = 40, message = ValidationMessages.NAME_MAX)
    @Column(name = "first_name")
    private String firstName;
    @Constraints.MinLength(value = 3, message = ValidationMessages.NAME_MIN)
    @Constraints.MaxLength(value = 40, message = ValidationMessages.NAME_MAX)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "access_level")
    private Integer accessLevel;
    @Column(name = "create_date", columnDefinition = "timestamp", updatable = false)
    private DateTime createDate;
    @Column(name = "created_by", length = 100)
    private String createdBy;
    @Column(name = "update_date", columnDefinition = "timestamp")
    private DateTime updateDate;
    @Column(name = "updated_by", length = 100)
    private String updatedBy;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Role> roles;
    //@OneToOne
    private Image image;

    private static final Finder<Long, User> FINDER = new Finder<>(User.class);

    /**
     * Public constructor for Ebean use
     */
    public User() {
        // leave emtpy
    }

    public static User findByEmail(String email) {
        return FINDER.where().eq(Constants.EMAIL, email).findUnique();
    }

    public static User createNewUser(UserRegistrationVM userRegistration) {
        if (userRegistration == null) {
            return null;
        }
        try {
            User user = new User();
            user.email = userRegistration.getEmail();
            user.password = userRegistration.getPassword();
            user.firstName = userRegistration.getFirstName();
            user.lastName = userRegistration.getLastName();
            user.createdBy = user.getFirstName();
            user.save();
            return user;
        } catch (PersistenceException e) {
            // TODO exception handling
            return null;
        }
    }

    public static User getUserForLogin(UserLoginVM userLogin) {
        User temp = FINDER.where().eq(Constants.EMAIL, userLogin.getEmail()).findUnique();
        if (temp == null) {
            return null;
        }
        if (Hash.checkPassword(userLogin.getPassword(), temp.password)) {
            return temp;
        }
        return null;
    }

    public static boolean authenticateLogin(String email, String password) {
        User user = FINDER.where().eq(Constants.EMAIL, email).findUnique();
        if (user == null) {
            return false;
        }
        if (Hash.checkPassword(password, user.password)) {
            return true;
        }
        return false;
    }

    public static boolean checkEmailIfExists(String email) {
        if (FINDER.where().eq(Constants.EMAIL, email).findUnique() == null) {
            return false;
        }
        return true;
    }

    public static boolean tableIsEmpty() {
        if (FINDER.findRowCount() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void save() {
        this.password = Hash.createHashedPassword(this.password);
        this.createDate = new DateTime();
        super.save();
    }

    @Override
    public void update() {
        this.updateDate = new DateTime();
        super.update();
    }

    public static Finder<Long, User> getFinder() {
        return FINDER;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public List<Role> getRoles() {
        return roles;
    }

    // SETTERS


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
