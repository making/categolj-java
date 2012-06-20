package am.ik.categolj.app.service.admin.amazon;

import java.util.List;

public class ProductResponse {
    private String error;
    private List<Product> products;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
