package models;

import com.avaje.ebean.Model;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.Logger;
import viewmodels.ArticleVM;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by boris on 11/29/15.
 */
@Entity
@Table(name = "article")
public class Article extends Model {

    private static Finder<Long, Article> finder = new Finder(Article.class);
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "sku", unique = true, nullable = false)
    private String sku;
    @Column(name = "availability")
    private Boolean availability;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    @Column(name = "prices")
    private List<Price> prices;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    @Column(name = "images")
    private List<Image> images;

    public Article() {
    }

    /**
     * Used for creating and saving new Article
     *
     * @param name
     * @param sku
     * @param availability
     * @param description
     */
    public Article(String name, String sku, Boolean availability, String description) {
        this.name = name;
        this.sku = sku;
        this.availability = availability;
        this.description = description;
        this.images = images;
    }

    public static List<Article> findAllArticles() {
        return finder.all();
    }

    public static Article getArticleById(Long id) {
        return finder.byId(id);
    }

    public static Article getArticleBySKU(String sku) {
        return finder.where().eq("sku", sku).findUnique();
    }

    public static List<Article> getAllActiveArticles() {
        return finder.where().eq("availability", true).findList();
    }

    public static Article createNewArticle(ArticleVM articleVM) {
        Article article = new Article(articleVM.getName(), articleVM.getProductCode(), articleVM.isAvailable(), articleVM.getDescription());
        try {
            article.save();
            return article;
        } catch (PersistenceException e) {
            Logger.error("Failed to save article" + e.getStackTrace());
            return null;
        }
    }

    public static boolean addImages(Article article, List<Image> images) {
        article.setImages(images);
        try {
            article.update();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to update article images" + e.getStackTrace());
            return false;
        }
    }

    public static boolean updateArticle(ArticleVM articleVM) {
        Article article = getArticleBySKU(articleVM.getProductCode());
        article.setName(articleVM.getName());
        article.setSku(articleVM.getProductCode());
        article.setAvailability(articleVM.isAvailable());
        article.setDescription(articleVM.getDescription());
        try {
            article.update();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to update article" + e.getStackTrace());
            return false;
        }
    }

    public static boolean deleteArticle(Long id) {
        Article article = finder.byId(id);
        if (article == null) {
            return false;
        }
        try {
            article.delete();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to delete article" + e.getStackTrace());
            return false;
        }
    }

    public static Float averagePrice(Article article) {
        int counter = 0;
        Float f = 0.0F;
        for(Price p : article.getPrices()) {
            counter++;
            f += p.getCost();
        }
        return f / counter;
    }

    public static Float currentPrice(Article article) {
        String date = new DateTime().toLocalDate() + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime toCheck = formatter.parseDateTime(date);
        Price price = Price.getFinder().where().eq("article", article).eq("price_date", toCheck).findUnique();
        if (price == null) {
            return null;
        }
        return price.getCost();
    }

    public static Float priceForSelectedDate(String selectedDate, String articleId) {
        Long id = null;
        if (selectedDate == null || articleId == null) {
            return null;
        }
        try {
            id = Long.parseLong(articleId);
        } catch (NumberFormatException e) {
            Logger.error("Failed to parse articleId String to Long" + e.getStackTrace());
        }
        Article article = getArticleById(id);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime toCheck = formatter.parseDateTime(selectedDate);
        Price price = Price.getFinder().where().eq("article", article).eq("price_date", toCheck).findUnique();
        if (price == null) {
            return null;
        }
        return price.getCost();
    }

    public static Finder<Long, Article> getFinder() {
        return finder;
    }

    public static void setFinder(Finder<Long, Article> finder) {
        Article.finder = finder;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
