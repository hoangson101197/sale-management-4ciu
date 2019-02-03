package com.codegym.fourciumanagerprocesssale.controller;

import com.codegym.fourciumanagerprocesssale.model.Product;
import com.codegym.fourciumanagerprocesssale.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public ModelAndView ProductList(@PageableDefault(size = 5) Pageable pageable) {
        Page<Product> products = productsService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("product/products");
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView formCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@Validated @ModelAttribute("product") Product product, @PathVariable("file") MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("product/create");
            return modelAndView;
        } else {
            product.setImage(uploadFiles(file));
            productsService.save(product);
            ModelAndView modelAndView = new ModelAndView("product/create");
            modelAndView.addObject("product", product);
            modelAndView.addObject("message", "Succesfully!");
            return modelAndView;
        }
    }

    private String uploadFiles(MultipartFile file) {
//        luu file vao o  cung
        File uploadFolder = new File("/home/sonnh/uploads");

//        Buoc 1: tao thu muc goc neu khong ton tai
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

//        Buoc 2: Lay ten cua file truoc khi upload
        String nameOfFileClient = file.getOriginalFilename();


        Date date = new Date();

//        tao ra ten cua anh sau khi luu vao o cung roi luu ten vao DB
        String nameOfFileServer = "";
        nameOfFileServer = String.valueOf((nameOfFileClient + date.toString()).hashCode());

//        Buoc 3: Luu file vao o cung
        if (nameOfFileClient != null && nameOfFileClient.length() > 0) {
            File imageFileServer = new File(uploadFolder.getAbsolutePath() + File.separator + nameOfFileServer);
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imageFileServer));
                stream.write(file.getBytes());
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nameOfFileServer;
    }
}
