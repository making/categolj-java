package am.ik.categolj.app.admin.service;

import java.util.List;

import am.ik.categolj.app.admin.service.amazon.Product;

public interface AmazonProductService {
    List<Product> searchByTitle(String title);

    List<Product> searchByKeyword(String keyword);
}
