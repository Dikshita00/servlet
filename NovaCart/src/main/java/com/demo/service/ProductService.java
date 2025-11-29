package com.demo.service;

import com.demo.bean.Product;
import com.demo.dao.ProductDAO;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> listProducts() throws Exception {
        return productDAO.listAll();
    }

    public Product get(int id) throws Exception {
        return productDAO.findById(id);
    }
}
