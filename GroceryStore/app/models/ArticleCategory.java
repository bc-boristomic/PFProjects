package models;

import com.avaje.ebean.Model;
import org.joda.time.DateTime;
import play.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by boris on 11/30/15.
 */
@Entity
@Table(name = "article_category")
public class ArticleCategory extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_date", columnDefinition = "datetime")
    private DateTime createDate;
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    private static Model.Finder<Long, ArticleCategory> finder = new Finder<>(ArticleCategory.class);

    public ArticleCategory() {
    }

    public static List<ArticleCategory> getAllRelations() {
        return finder.all();
    }

    public static ArticleCategory getRelationById(Long id) {
        return finder.where().eq("id", id).findUnique();
    }

    public static List<Category> getCategoriesByArticle(Article article) {
        List<ArticleCategory> articleCategories = finder.where().eq("article", article).findList();
        List<Category> categories = new ArrayList<>();
        for(ArticleCategory ac : articleCategories) {
            if (ac.article.equals(article)) {
                categories.add(ac.getCategory());
            }
        }
        return categories;
    }


    public static boolean createNewCategory(Long categoryId, Long articleId) {
        ArticleCategory relation = new ArticleCategory();
        Category tempCategory = Category.getCategoryById(categoryId);
        Article tempArticle = Article.getArticleById(articleId);
        relation.setArticle(tempArticle);
        relation.setCategory(tempCategory);
        try {
            relation.save();
            return true;
        }catch (PersistenceException e) {
            Logger.error("Failed to save relation" + e.getStackTrace());
            return false;
        }
    }

    public static boolean deleteRelation(Long id) {
        ArticleCategory relation = getRelationById(id);
        if (relation == null) {
            return false;
        }
        try{
            relation.delete();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to delete relation" + e.getStackTrace());
            return false;
        }
    }

    @Override
    public void save() {
        setCreateDate(new DateTime());
        super.save();
    }

    public Article getArticle() {
        return article;
    }

    public Category getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Finder<Long, ArticleCategory> getFinder() {
        return finder;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }


}
