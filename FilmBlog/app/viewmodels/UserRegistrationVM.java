package viewmodels;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import resources.ValidationMessages;
import utils.Patterns;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by boris on 11/19/15.
 */
public class UserRegistrationVM {

    @Constraints.Required(message = ValidationMessages.PASSWORD_REQUIRED)
    @Constraints.Pattern(value = Patterns.PASSWORD, message = ValidationMessages.PASSWORD_PATTERN)
    protected String password;
    @Constraints.Required(message = ValidationMessages.EMAIL_REQUIRED)
    @Constraints.Pattern(value = Patterns.EMAIL, message = ValidationMessages.EMAIL_PATTERN)
    private String email;
    private String passwordRetyped;
    @Constraints.MinLength(value = 3, message = ValidationMessages.NAME_MIN)
    @Constraints.MaxLength(value = 40, message = ValidationMessages.NAME_MAX)
    private String firstName;
    @Constraints.MinLength(value = 3, message = ValidationMessages.NAME_MIN)
    @Constraints.MaxLength(value = 40, message = ValidationMessages.NAME_MAX)
    private String lastName;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if (!this.password.equals(this.passwordRetyped)) {
            errors.add(new ValidationError("password", "Passwords must match."));
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

    public String getPasswordRetyped() {
        return passwordRetyped;
    }

    public void setPasswordRetyped(String passwordRetyped) {
        this.passwordRetyped = passwordRetyped;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
