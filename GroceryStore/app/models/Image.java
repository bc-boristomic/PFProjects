package models;

import com.avaje.ebean.Model;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import play.Logger;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by boris on 11/29/15.
 */
@Entity
@Table(name = "image")
public class Image extends Model {

    private static Finder<Long, Image> finder = new Finder<>(Image.class);

    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "public_id")
    private String publicId;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "secret_image_url")
    private String secretImageUrl;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Article article;

    public static Cloudinary cloudinary;

    public Image() {

    }

    public Image(String publicId, String imageUrl, String secretImageUrl) {
        this.publicId = publicId;
        this.imageUrl = imageUrl;
        this.secretImageUrl = secretImageUrl;
    }

    public static Image create(String publicId, String imageUrl, String secretImageUrl) {
        Image i = new Image(publicId, imageUrl, secretImageUrl);
        try {
            i.save();
        } catch (PersistenceException e) {
            Logger.debug("Failed to save image " + e.getStackTrace());
        }
        return i;
    }

    public static Image create(File image, Article article) {
        Map result;
        try {
            result = cloudinary.uploader().upload(image, Cloudinary.emptyMap());
            return create(result, article);
        } catch (IOException e) {
            Logger.debug("Failed to save image " + e.getStackTrace());
        }
        return null;
    }

    public static Image create(Map uploadResult, Article article) {
        Image i = new Image();
        i.setPublicId((String) uploadResult.get("public_id"));
        i.setImageUrl((String) uploadResult.get("url"));
        i.setSecretImageUrl((String) uploadResult.get("secure_url"));
        i.setArticle(article);
        try {
            i.save();
        } catch (PersistenceException e) {
            Logger.debug("Failed to save image " + e.getStackTrace());
        }
        return i;
    }

    public static List<Image> all() {
        return finder.all();
    }

    public String getSize(int width, int height) {

        String url = cloudinary.url().format("jpg")
                .transformation(new Transformation().width(width).height(height).crop("fit")).generate(publicId);

        return url;
    }

    public void deleteImage() {

        try {
            cloudinary.uploader().destroy(publicId, null);
        } catch (IOException e) {
            Logger.debug("Failed to delete image " + e.getStackTrace());
        }

    }


    public Long getId() {
        return id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSecretImageUrl() {
        return secretImageUrl;
    }

    public void setSecretImageUrl(String secretImageUrl) {
        this.secretImageUrl = secretImageUrl;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public static Finder<Long, Image> getFinder() {
        return finder;
    }

}
