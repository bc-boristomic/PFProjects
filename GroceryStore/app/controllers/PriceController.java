package controllers;

import models.Article;
import models.Price;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.price;

import java.util.List;

/**
 * Created by boris on 11/30/15.
 */
public class PriceController extends Controller {

    public Result setPrice(Long articleId) {
        Article article = Article.getArticleById(articleId);
        List<Article> articleList = Article.getAllActiveArticles();
        return ok(price.render(articleList, article));
    }

    public Result savePrice(Long articleId) {
        DynamicForm form = Form.form().bindFromRequest();
        String date = form.field("date").value();
        String price = form.field("price").value();

        if(!Price.checkDate(date, articleId)) {
            flash("error", "Price with selected date already exists for selected product.");
            return setPrice(-1L);
        }
        if(Price.createNewPrice(price, date, articleId)) {
            return setPrice(-1L);
        }

        return internalServerError();
    }

    public Result deletePrice(Long id) {
        if (Price.deletePrice(id)) {
            return setPrice(-1L);
        }
        return internalServerError();
    }


}
