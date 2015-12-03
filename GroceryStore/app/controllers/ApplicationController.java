package controllers;

import models.Article;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.report;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ApplicationController extends Controller {


    public Result index() {
        return ok(index.render());
    }

    public Result allReports() {
        List<Article> articles = Article.findAllArticles();
        return ok(report.render(articles, null));
    }

    public Result singleReport(Long articleId) {
        Article article = Article.getArticleById(articleId);
        List<Article> articles = Article.findAllArticles();
        return ok(report.render(articles, article));
    }

    public Result currentDayPrice() {
        DynamicForm form = Form.form().bindFromRequest();
        String selectedDate = form.field("selected").value();
        String articleId = form.field("id").value();
        Float price = Article.priceForSelectedDate(selectedDate, articleId);
        if (price == null) {
            return internalServerError();
        }
        return ok(String.valueOf(price));
    }

}
