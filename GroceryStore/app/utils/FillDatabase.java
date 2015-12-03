package utils;

import models.Article;
import models.Category;

import java.util.UUID;

/**
 * Created by boris on 12/2/15.
 */
public class FillDatabase {

    public static void createArticles() {
        if (Article.getFinder().findRowCount() == 0) {
            for (int i = 0; i < 10; i++) {
                Article article = new Article();
                if (i == 0) {
                    article.setName("Carrot");
                } else if (i == 1) {
                    article.setName("Apple");
                } else if (i == 2) {
                    article.setName("Orange");
                } else {
                    article.setName("Generated article " + i);
                }
                article.setSku(UUID.randomUUID().toString());
                article.setDescription("Article description");
                if (i > 2 && i % 2 == 0) {
                    article.setAvailability(true);
                } else if (i > 2 && i % 2 != 0) {
                    article.setAvailability(false);
                } else {
                    article.setAvailability(true);
                }
                article.save();
            }
        }
    }

    public static void createCategories() {
        if (Category.getFinder().findRowCount() == 0) {
            Category category;
            for (int i = 0; i < 5; i++) {
                if (i == 0) {
                    category = new Category("Food", true);
                } else if (i == 1) {
                    category = new Category("Fruits", true);
                } else if (i == 2) {
                    category = new Category("Vegetables", true);
                } else {
                    category = new Category("Generated category " + i, false);
                }
                category.save();
            }
        }
    }
}
