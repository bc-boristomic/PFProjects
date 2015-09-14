package models;

import play.data.validation.Constraints;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by boris.tomic on 14/09/15.
 */
public class Tag {

    public static List<Tag> tags = new LinkedList<>();

    static {
        tags.add(new Tag(1L, "lightweight", Product.findByName("Paperclips 1")));
        tags.add(new Tag(2L, "metal", Product.findByName("Paperclips 2")));
        tags.add(new Tag(3L, "plastic", Product.findByName("Paperclips 3")));

    }

    public Long id;
    @Constraints.Required
    public String name;
    public List<Product> products;

    public Tag() {
    }

    public Tag(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public static Tag findById(Long id) {
        for (Tag tag : tags) {
            if (tag.id.equals(id)) {
                return tag;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
