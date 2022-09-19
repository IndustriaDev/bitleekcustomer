package com.example.bitleekcustomer.controllers;

import com.example.bitleekcustomer.models.Product;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.example.bitleekcustomer.constants.URIs.URI_PRODUCT;

@Scope(value = "session")
@Component(value = "productController")
@ELBeanName(value = "productController")
@Join(path = "/", to = "/product/product-list.jsf")
public class ProductController {

    @Autowired
    RestTemplate restTemplate;

    private Product[] products;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        try {
            products = restTemplate.getForObject(URI_PRODUCT, Product[].class);
        } catch (Exception e) {
            return;
        }
    }

    public List<Product> getProducts() {
        return Arrays.asList(products);
    }

    public String delete(Product product) {
        //productRepository.delete(product.getId());
        loadData();
        return null;
    }
}
