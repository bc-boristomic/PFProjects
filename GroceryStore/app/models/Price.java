package models;

import com.avaje.ebean.Model;
import org.joda.time.DateTime;
import play.Logger;

import javax.persistence.*;
import java.util.List;

/**
 * Created by boris on 11/29/15.
 */
@Entity
@Table(name = "price")
public class Price extends Model {

    private static Finder<Long, Price> finder = new Finder(Price.class);
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "price_date", columnDefinition = "datetime")
    private DateTime priceDate;
    @Column(name = "cost")
    private Float cost;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Article article;

    public Price() {

    }

    public Price(Float cost, DateTime date, Article article) {
        this.cost = cost;
        this.priceDate = date;
        this.article = article;
    }

    public static List<Price> findAllPrices() {
        return finder.all();
    }

    public static boolean createNewPrice(String price, String date, Long articleId) {
        if (price != null && date != null) {
            Article article = Article.getArticleById(articleId);
            Price priceToSave = new Price(Float.parseFloat(price), DateTime.parse(date), article);
            try {
                priceToSave.save();
                return true;
            } catch (PersistenceException e) {
                Logger.error("Failed to save price" + e.getStackTrace());
                return false;
            }
        }
        return false;
    }

    public static boolean deletePrice(Long priceId) {
        Price price = findPriceById(priceId);
        if (price == null) {
            return false;
        }
        try {
            price.delete();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to delete price" + e.getStackTrace());
            return false;
        }
    }

    private static Price findPriceById(Long priceId) {
        return finder.byId(priceId);
    }

    public static Finder<Long, Price> getFinder() {
        return finder;
    }

    public static boolean checkDate(String date, Long id) {
        Article article = Article.getArticleById(id);
        if (finder.where().eq("article", article).eq("price_date", DateTime.parse(date)).findRowCount() == 0) {
            return true;

        }
        return false;
    }

    public Article getArticle() {
        return article;
    }

    public Long getId() {
        return id;
    }

    public Float getCost() {
        return cost;
    }

    public DateTime getPriceDate() {
        return priceDate;
    }
}
