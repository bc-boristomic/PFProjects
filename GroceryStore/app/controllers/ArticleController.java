package controllers;

import models.Article;
import models.Image;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import viewmodels.ArticleVM;
import views.html.article;

import java.io.File;
import java.util.ArrayList;
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
            Article saved = null;
            if ((saved = Article.createNewArticle(articleVM)) != null) {
                List<Image> images = new ArrayList<>();
                Http.MultipartFormData body = request().body().asMultipartFormData();
                List<Http.MultipartFormData.FilePart> fileParts = body.getFiles();
                if(fileParts != null){
                    for(Http.MultipartFormData.FilePart singleFile : fileParts) {
                        File image = singleFile.getFile();
                        Image temp = Image.create(image, saved);
                        if (temp != null) {
                            images.add(temp);
                        }
                    }
                }
                if (Article.addImages(saved, images)) {
                    return allArticles();
                }

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


