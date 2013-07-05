package am.ik.categolj.domain.service.product;

import java.util.List;


public interface AmazonProductService {
    List<Product> searchByTitle(String title);

    List<Product> searchByKeyword(String keyword);
}
