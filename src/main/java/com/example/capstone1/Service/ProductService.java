package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    //Array List for products
    ArrayList<Product> products = new ArrayList<>();

    //Get Products
    public ArrayList<Product> getProducts() {
        return products;
    }

    //Add Product
    public void addProduct(Product product) {
        products.add(product);
    }

    //Update product
    public boolean updateProduct(int id ,Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    //Delete product
    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
}
