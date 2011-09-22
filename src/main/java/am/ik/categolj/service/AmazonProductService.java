package am.ik.categolj.service;

import java.util.List;

import am.ik.categolj.service.amazon.Product;

public interface AmazonProductService {
    List<Product> searchByTitle(String title);

    List<Product> searchByKeyword(String keyword);
}
