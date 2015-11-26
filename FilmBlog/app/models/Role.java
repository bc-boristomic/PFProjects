package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by boris on 11/21/15.
 */
@Entity
@Table(name = "role")
public final class Role extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", length = 10, unique = true)
    private String name;

    private static final Finder<Long, Role> FINDER = new Finder<>(Role.class);

    /**
     * Public constructor for Ebean use
     */
    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public static List<Role> getAllRoles() {
        return FINDER.all();
    }

    public String getName() {
        return name;
    }

    public static Finder<Long, Role> getFINDER() {
        return FINDER;
    }
}
