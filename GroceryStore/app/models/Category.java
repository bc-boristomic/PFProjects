package models;

import com.avaje.ebean.Model;
import play.Logger;

import javax.persistence.*;
import java.util.List;

/**
 * Created by boris on 11/30/15.
 */
@Entity
@Table(name = "category")
public class Category extends Model {

    private static Finder<Long, Category> finder = new Finder(Category.class);
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "availability")
    private Boolean availability;

    public Category() {

    }

    public Category(String name, Boolean availability) {
        this.name = name;
        this.availability = availability;
    }

    public static List<Category> getAllCategories() {
        return finder.all();
    }

    public static List<Category> getAllActiveCategories() {
        return finder.where().eq("availability", true).findList();
    }

    public static Category getCategoryById(Long id) {
        return finder.where().eq("id", id).findUnique();
    }


    public static boolean createNewCategory(String name, boolean status) {
        Category temp = new Category(name, status);
        try {
            temp.save();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to save category"  + e.getStackTrace());
            return false;
        }

    }

    public static boolean updateCategory(Long id, String name, boolean status) {
        Category temp = getCategoryById(id);
        temp.setName(name);
        temp.setAvailability(status);
        try {
            temp.update();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to update category" + e.getStackTrace());
            return false;
        }

    }

    public static boolean deleteCategory(Long id) {
        Category temp = getCategoryById(id);
        if (temp == null) {
            return false;
        }
        try {
            temp.delete();
            return true;
        } catch (PersistenceException e) {
            Logger.error("Failed to delete category" + e.getStackTrace());
            return false;
        }
    }

    public static Finder<Long, Category> getFinder() {
        return finder;
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

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
