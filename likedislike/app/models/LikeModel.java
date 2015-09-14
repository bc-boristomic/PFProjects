package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by boris.tomic on 14/09/15.
 */
@Entity
public class LikeModel extends Model {

    @Id
    private Integer id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "ip_address")
    private String ipAddress;

    private static Finder<Integer, LikeModel> finder = new Finder<>(LikeModel.class);

    public static List<LikeModel> findAll() {
        return finder.all();
    }

    public Boolean getStatus() {
        return status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Integer getId() {
        return id;
    }


}
