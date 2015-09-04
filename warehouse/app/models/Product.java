package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

//import play.data.validation.*;

/**
 * Created by boris.tomic on 28/08/15.
 */
@Entity
public class Product {

    private static List<Product> products;

    static {
        products = new ArrayList<Product>();
        products.add(new Product("1111", "Paperclips 1", "Paperclips description 1"));
        products.add(new Product("2222", "Paperclips 2", "Paperclips description 2"));
        products.add(new Product("3333", "Paperclips 3", "Paperclips description 3"));
        products.add(new Product("4444", "Paperclips 4", "Paperclips description 4"));
        products.add(new Product("5555", "Paperclips 5", "Paperclips description 5"));
    }

    @Constraints.Required
    public String ean;

    @Constraints.Required
    public String name;


    public String description;

    /**
     * Default constructor
     */
    public Product() /*extends Model*/ {

    }

    /**
     * Constructor used to create object
     *
     * @param ean
     * @param name
     * @param description
     */
    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public static void saveToList(Product product) {
        products.add(product);
    }

    public static List<Product> findAll() {
        return new ArrayList<Product>(products);
    }

    public static Product findById(String ean) {
        for (Product p : products) {
            if (p.ean.equals(ean)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> findByName(String name) {
        final List<Product> results = new ArrayList<Product>();
        for (Product p : products) {
            if (p.name.toLowerCase().contains(name)) {
                results.add(p);
            }
        }
        return results;
    }

    public static boolean remove(Product product) {
        return products.remove(product);
    }

    public void save() {
        products.remove(findById(this.ean));
        products.add(this);
    }

    public String toString() {
        return String.format("%s - %s", ean, name);
    }

}
