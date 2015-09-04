package controllers;

import models.Apartment;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.Logger;

import views.html.newPlace;
import views.html.index;
import views.html.list;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.avaje.ebean.*;

public class Application extends Controller {

    private static final Form<Apartment> apartmentForm = Form.form(Apartment.class);



    public Result index() {
        return ok(index.render(""));
    }

    public Result view() {
        List<Apartment> apartmentList = Apartment.findAll();
        Logger.info(apartmentList.toString());
        return ok(list.render(apartmentList));
    }

    public Result save() {
        Form<Apartment> boundForm = apartmentForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("warning", "Please correct form.");
            return badRequest(newPlace.render(apartmentForm));
        }
        String name = boundForm.get().name;
        BigDecimal price = boundForm.get().rent;
        String location = boundForm.get().location;
        Double sqr = boundForm.get().square;
        Integer rooms = boundForm.get().roomNumber;

        Logger.info("rooms " + rooms);
        Apartment apartment = new Apartment(name, price, location, sqr, rooms);

        Ebean.save(apartment);

        return redirect(routes.Application.view());
    }

    public Result newAppartment() {
        return ok(newPlace.render(apartmentForm));
    }

    public Result search(Long id) {
        List<Apartment> list2 = new ArrayList<Apartment>();
        list2.add(Apartment.findById(id));
        return ok(list.render(list2));
    }



}
