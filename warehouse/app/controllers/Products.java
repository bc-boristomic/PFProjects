package controllers;

import models.Product;
import models.Tag;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.details;
import views.html.products.list;

import java.util.LinkedList;
import java.util.List;

public class Products extends Controller {

    private static final Form<Product> productForm = Form.form(Product.class);

    public Result list() {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    public Result newProduct() {
        return ok(details.render(productForm));
    }

    public Result details(String ean) {
        final Product product = Product.findById(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exists.", ean));
        }
        Form<Product> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }

    public Result save() {
        Form<Product> boundForm = productForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("warning", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();

        List<Tag> tags = new LinkedList<>();
        List<Tag> oldTags = new LinkedList<>();

        for (Tag tag : product.tags) {
            if (tag.id != null) {
                tags.add(Tag.findById(tag.id));
            }
            oldTags.add(tag);
        }
        product.tags = tags;

        product.save();

        flash("success", String.format("Successfully added product %s", product));

        Logger.info(product.toString());

        return redirect(routes.Products.list());
    }

    public Result delete(String ean) {
        final Product product = Product.findById(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exists.", ean));
        }
        Product.remove(product);
        return redirect(routes.Products.list());
    }

}