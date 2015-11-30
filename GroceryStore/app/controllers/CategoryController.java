package controllers;

import models.Category;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.category;

import java.util.List;

/**
 * Created by boris on 11/29/15.
 */
public class CategoryController extends Controller {

    public Result allCategories() {
        List<Category> categoryList = Category.getAllCategories();
        return ok(category.render(categoryList, null));
    }


    public Result editCategory(Long id) {
        List<Category> categoryList = Category.getAllCategories();
        Category singleCategory = Category.getCategoryById(id);
        return ok(category.render(categoryList, singleCategory));
    }

    public Result deleteCategory(Long id) {
        if (Category.deleteCategory(id)) {
            return allCategories();
        }
        return internalServerError();
    }

    public Result addUpdateCategory(Long id) {
        DynamicForm form = Form.form().bindFromRequest();
        String name = form.field("name").value();
        String active = form.field("isAvailable").value();

        boolean activeCategory = checkIfStatusIsOn(active);

        if(id != -1) {
            if (Category.updateCategory(id, name, activeCategory)) {
                return allCategories();
            }
            return internalServerError();
        }
        if (Category.createNewCategory(name, activeCategory)) {
            return allCategories();

        }
        return internalServerError();
    }

    private boolean checkIfStatusIsOn(String fromForm) {
        if ("on".equals(fromForm)) {
            return true;
        }
        return false;
    }
}
