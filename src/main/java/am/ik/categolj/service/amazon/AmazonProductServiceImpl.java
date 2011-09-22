package am.ik.categolj.service.amazon;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import am.ik.aws.apa.AwsApaRequester;
import am.ik.aws.apa.jaxws.Image;
import am.ik.aws.apa.jaxws.Item;
import am.ik.aws.apa.jaxws.ItemAttributes;
import am.ik.aws.apa.jaxws.ItemSearchRequest;
import am.ik.aws.apa.jaxws.ItemSearchResponse;
import am.ik.aws.apa.jaxws.Items;
import am.ik.categolj.service.AmazonProductService;
import am.ik.yalf.logger.Logger;

public class AmazonProductServiceImpl implements AmazonProductService {
    @Inject
    protected AwsApaRequester requester;

    private static final Logger LOGGER = Logger
            .getLogger(AmazonProductServiceImpl.class);

    protected List<Product> searchProduct(ItemSearchRequest request) {
        request.getResponseGroup().add("Large");
        List<Product> products = new ArrayList<Product>();
        ItemSearchResponse res = requester.itemSearch(request);
        for (Items items : res.getItems()) {
            for (Item item : items.getItem()) {
                Product product = new Product();
                ItemAttributes attributes = item.getItemAttributes();
                Image image = item.getMediumImage();
                product.setAsin(item.getASIN());
                product.setLink(item.getDetailPageURL());
                if (image != null) {
                    product.setImageLink(image.getURL());
                }
                if (attributes != null) {
                    product.setTitle(attributes.getTitle());
                    product.setAuthors(attributes.getAuthor());
                    product.setPublicationDate(attributes.getPublicationDate());
                }
                products.add(product);
            }
        }
        LOGGER.debug(false, "[DCTGLX10] products={0}", products);
        return products;
    }

    @Override
    public List<Product> searchByTitle(String title) {
        LOGGER.debug(false, "[DCTGLX11] search by title={0}", title);
        ItemSearchRequest request = new ItemSearchRequest();
        request.setTitle(title);
        request.setSearchIndex("Books");
        List<Product> products = searchProduct(request);
        request.setSearchIndex("ForeignBooks");
        products.addAll(searchProduct(request));
        return products;
    }

    @Override
    public List<Product> searchByKeyword(String keyword) {
        LOGGER.debug(false, "[DCTGLX12] search by keyword={0}", keyword);
        ItemSearchRequest request = new ItemSearchRequest();
        request.setKeywords(keyword);
        request.setSearchIndex("Books");
        List<Product> products = searchProduct(request);
        request.setSearchIndex("ForeignBooks");
        products.addAll(searchProduct(request));
        return products;
    }

}
