package controllers;

import models.Article;
import models.ArticleCategory;
import models.Category;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.relation;

import java.util.List;

/**
 * Created by boris on 11/29/15.
 */
public class RelationController extends Controller {

    public Result allRelations() {
        List<Category> categories = Category.getAllActiveCategories();
        List<Article> articles = Article.getAllActiveArticles();
        List<ArticleCategory> relations = ArticleCategory.getAllRelations();
        return ok(relation.render(relations, categories, articles));
    }

    public Result saveRelation() {
        DynamicForm form = Form.form().bindFromRequest();
        List<Article> articles = Article.getAllActiveArticles();
        String category = form.field("category").value();
        Long catId = null;
        Long artId = null;
        if (category != null) {
            try {
                catId = Long.parseLong(category);
            } catch (NumberFormatException e) {
                Logger.error("Failed to parse category id" + e.getStackTrace());
                return internalServerError();
            }
        }

        boolean allSaved = false;
        for (int i = 0; i < articles.size(); i++) {
            String article = form.field(articles.get(i).getId().toString()).value();

            if (article != null) {
                try {
                    artId = Long.parseLong(article);
                } catch (NumberFormatException e) {
                    Logger.error("Failed to parse category id" + e.getStackTrace());
                    return internalServerError();
                }
                if (ArticleCategory.createNewCategory(catId, artId)) {
                    allSaved = true;
                }
            }
        }
        if (allSaved) {
            return allRelations();
        }
        return internalServerError();
    }

    public Result deleteRelation(Long id) {
        if (ArticleCategory.deleteRelation(id)) {
            return allRelations();
        }
        return internalServerError();
    }


}
