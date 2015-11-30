package controllers;

import models.Article;
import play.*;
import play.data.Form;
import play.mvc.*;

import viewmodels.ArticleVM;
import views.html.*;

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

}
