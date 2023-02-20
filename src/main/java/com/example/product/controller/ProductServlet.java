package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import com.example.product.service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    public static ProductService productService = new ProductServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                newProductForm(request, response);
                break;
            case "edit":
                editProductForm(request, response);
                break;
            case "delete":
                deleteProductForm(request, response);
                break;
            case "view":
                viewProductForm(request, response);
                break;
            default:
                displayProductList(request, response);
                break;
        }
    }

    private void deleteProductForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);

        RequestDispatcher dispatcher;

        if (product == null) {
            dispatcher = request.getRequestDispatcher("product/404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editProductForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.findById(id);

        RequestDispatcher dispatcher;

        if (product == null) {
            dispatcher = request.getRequestDispatcher("product/404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void newProductForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayProductList(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void viewProductForm(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);

        RequestDispatcher dispatcher;

        if (product == null) {
            dispatcher = request.getRequestDispatcher("product/404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/view.jsp");

            try {
                dispatcher.forward(request, response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        Product product = productService.findByName(name);
        RequestDispatcher dispatcher;

        if (product == null) {
            dispatcher = request.getRequestDispatcher("product/404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/search.jsp");

            try {
                dispatcher.forward(request, response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
       int id = Integer.parseInt(request.getParameter("id"));

       Product product = productService.findById(id);

       RequestDispatcher dispatcher;

        if (product == null) {
            dispatcher = request.getRequestDispatcher("product/404.jsp");
        } else {
            productService.remove(id);
            try {
                response.sendRedirect("/products");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Product product = productService.findById(id);
        RequestDispatcher dispatcher;

        if (product == null) {
            dispatcher = request.getRequestDispatcher("product/404.jsp");
        } else {
            product.setId(id);
            product.setName(name);
            productService.update(id, product);

            request.setAttribute("message", "Update success!");
        }
        try {
            response.sendRedirect("/products");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int id = productService.getSize();
        Product product = new Product(id, name);
        productService.save(product);
        request.setAttribute("message", "New product was created");
        try {
            response.sendRedirect("/products");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
