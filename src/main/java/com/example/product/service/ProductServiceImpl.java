package com.example.product.service;

import com.example.product.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static ArrayList<Product> productList;

    static {
        productList = new ArrayList<>();
        productList.add(new Product(1, "iphone 5"));
        productList.add(new Product(2, "iphone 6"));
        productList.add(new Product(3, "iphone 7"));
        productList.add(new Product(4, "iphone 8"));
        productList.add(new Product(5, "iphone 9"));
        productList.add(new Product(6, "iphone 10"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
        productList.trimToSize();
    }

    @Override
    public void update(int id, Product product) {
        productList.set(id, product);
    }

    @Override
    public void remove(int id) {
        productList.remove(id);
        for (Product product : productList) {
            product.setId(productList.indexOf(product));
        }
    }

    @Override
    public Product findById(int id) {
        return productList.get(id);
    }

    @Override
    public Product findByName(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public int getSize() {
        return productList.size();
    }
}
