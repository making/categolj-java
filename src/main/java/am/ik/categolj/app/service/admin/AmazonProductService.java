package am.ik.categolj.app.service.admin;

import java.util.List;

import am.ik.categolj.app.service.admin.amazon.Product;

public interface AmazonProductService {
    List<Product> searchByTitle(String title);

    List<Product> searchByKeyword(String keyword);
}
