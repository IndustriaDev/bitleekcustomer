package com.example.bitleekcustomer.controllers;

import com.example.bitleekcustomer.models.Category;
import com.example.bitleekcustomer.models.Product;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static com.example.bitleekcustomer.constants.URIs.URI_PRODUCT;

@Scope(value = "session")
@Component(value = "productSaveController")
@ELBeanName(value = "productSaveController")
@Join(path = "/product", to = "/product/product-form.jsf")
public class ProductSaveController {

    @Autowired
    RestTemplate restTemplate;

    private Product product = new Product();

    public String save() {
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        LocalDateTime fecha = LocalDateTime.of(hoy, ahora);

        product.setCategory(new Category(1L, "Comida", fecha));

        Product createdProduct = restTemplate.postForObject(URI_PRODUCT, product, Product.class);
        if (createdProduct != null) {
            product = new Product();
            return "/product-list.xhtml?faces-redirect=true";
        }
        return "/product/product-form.xhtml";
    }

    public Product getProduct() {
        return product;
    }
}
