package viewmodels;

import models.User;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import resources.ValidationMessages;
import utils.Patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boris on 11/19/15.
 */
public class UserLoginVM {

    @Constraints.Required(message = ValidationMessages.EMAIL_REQUIRED)
    @Constraints.Pattern(value = Patterns.EMAIL, message = ValidationMessages.EMAIL_PATTERN)
    private String email;
    @Constraints.Required(message = ValidationMessages.PASSWORD_REQUIRED)
    @Constraints.Pattern(value = Patterns.PASSWORD, message = ValidationMessages.PASSWORD_PATTERN)
    private String password;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();
        if (!User.authenticateLogin(email, password)) {
            errors.add(new ValidationError("bad login", "Wrong email or password."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
