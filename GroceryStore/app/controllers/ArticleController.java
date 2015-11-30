package controllers;

import models.Article;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import viewmodels.ArticleVM;
import views.html.article;

import java.util.List;

/**
 * Created by boris on 11/29/15.
 */
public class ArticleController extends Controller {

    private static final Form<ArticleVM> ARTICLE_VM_FORM = Form.form(ArticleVM.class);

    public Result allArticles() {
        List<Article> articles = Article.findAllArticles();
        return ok(article.render(articles, ARTICLE_VM_FORM, null));
    }

    public Result editArticle(Long id) {
        Article temp = Article.getArticleById(id);
        List<Article> articles = Article.findAllArticles();
        return ok(article.render(articles, ARTICLE_VM_FORM, temp));
    }


    public Result addUpdateArticle() {
        Form<ArticleVM> submitted = ARTICLE_VM_FORM.bindFromRequest();
        if (submitted.hasErrors()) {
            List<Article> articles = Article.findAllArticles();
            return badRequest(article.render(articles, submitted, null));
        }
        ArticleVM articleVM = submitted.get();
        if (Article.getArticleBySKU(articleVM.getProductCode()) == null) {
            if (Article.createNewArticle(articleVM)) {
                return allArticles();
            }
            return internalServerError("failed to save article");
        }
        if (Article.updateArticle(articleVM)) {
            return allArticles();
        }
        return internalServerError("failed to update article");
    }

    public Result deleteArticle(Long id) {
        if (Article.deleteArticle(id)) {
            return allArticles();
        }
        flash("error", "Failed to delete article.");
        return redirect(routes.ApplicationController.index());
    }


}


