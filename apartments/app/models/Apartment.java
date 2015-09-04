package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by boris.tomic on 03/09/15.
 */
@Entity
public class Apartment extends Model {

    public static Finder<String, Apartment> finder = new Finder<String, Apartment>(String.class, Apartment.class);

    public static List<Apartment> apartments = new ArrayList<Apartment>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Column(name = "name", length = 100)
    public String name;

    //@Size(min=50,max=5000)
    @Column(name = "rent", precision = 10, scale = 2)
    public BigDecimal rent;

    @Column(name = "location", length = 500)
    public String location;

    //@Range(min=25,max=500)
    @Column(name = "square")
    public Double square;

    //@Min(1)
    @Column(name = "rooms", length = 1)
    public Integer roomNumber;

    public Apartment() {

    }

    public Apartment(String name, BigDecimal rent, String location, Double square, Integer roomNumber) {
        this.name = name;
        this.rent = rent;
        this.location = location;
        this.square = square;
        this.roomNumber = roomNumber;
    }


    public static List<Apartment> findAll() {
        return new ArrayList<Apartment>(finder.all());
    }

    public static Apartment findById(Long id) {
        List<Apartment> apartment = finder.where().eq("id", id.toString()).findList();
        if (apartment.size() == 0) {
            return null;
        }
        return (Apartment) apartment.get(0);
    }

    public String toString(){
        return String.format("Number of rooms %d", roomNumber);
    }

}
