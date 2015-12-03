package viewmodels;

import play.data.validation.ValidationError;
import utils.ResourceStrings;
import utils.Validations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boris on 11/29/15.
 */
public class ArticleVM {

    private String name;
    private String productCode;
    private boolean isAvailable;
    private String description;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();
        if (Validations.isStringEmpty(name)) {
            errors.add(new ValidationError("entry", ResourceStrings.ARTICLE_NAME));
        } else if (Validations.isStringEmpty(productCode)) {
            errors.add(new ValidationError("entry", ResourceStrings.ARTICLE_SKU));
        } else if (Validations.isStringEmpty(description)) {
            errors.add(new ValidationError("entry", ResourceStrings.ARTICLE_DESCRIPTION));
        }
        return errors.isEmpty() ? null : errors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
