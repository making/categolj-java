package am.ik.categolj.app.admin;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.domain.service.product.AmazonProductService;
import am.ik.categolj.domain.service.product.Product;
import am.ik.categolj.domain.service.product.ProductResponse;

@Controller
@RequestMapping("/apa/*")
public class AmazonProductController {
    @Inject
    protected AmazonProductService amazonProductService;

    @RequestMapping("/search/title/{title}")
    public @ResponseBody
    ProductResponse searchByTitle(@PathVariable String title) {
        ProductResponse response = new ProductResponse();
        try {
            List<Product> products = amazonProductService.searchByTitle(title);
            response.setProducts(products);
        } catch (Exception e) {
            response.setError(e.getMessage());
        }
        return response;
    }

    @RequestMapping("/search/keyword/{keyword}")
    public @ResponseBody
    ProductResponse searchByKeyword(@PathVariable String keyword) {
        ProductResponse response = new ProductResponse();
        try {
            List<Product> products = amazonProductService
                    .searchByKeyword(keyword);
            response.setProducts(products);
        } catch (Exception e) {
            response.setError(e.getMessage());
        }
        return response;
    }
}
