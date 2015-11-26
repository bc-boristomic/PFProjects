package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by boris on 11/19/15.
 */
//@Entity
//@Table(name = "image")
public final class Image extends Model {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;
//    @Column(name = "public_id")
    public String publicId;
//
//    public String image_url;
//
//    public String secret_image_url;
//
//    public boolean isPublished;
//
//
//    public static Cloudinary cloudinary;

    /**
     * Public constructor for Ebean use
     */
    public Image() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
