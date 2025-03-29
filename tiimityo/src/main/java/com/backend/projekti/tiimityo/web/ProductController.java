package com.backend.projekti.tiimityo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.projekti.tiimityo.domain.Manufacturer;
import com.backend.projekti.tiimityo.domain.ManufacturerRepository;
import com.backend.projekti.tiimityo.domain.Product;
import com.backend.projekti.tiimityo.domain.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository prepository;
    @Autowired
    private ManufacturerRepository mrepository;

    // Get all products:
    @GetMapping("/productlist")
    public String getProductList(Model model) {
        model.addAttribute("products", prepository.findAll());
        model.addAttribute("manufacturers", mrepository.findAll());
        return "productlist";
    }

    // Edit product:
    @RequestMapping("edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        model.addAttribute("product", prepository.findById(productId));
        model.addAttribute("manufacturers", mrepository.findAll());
        return "editproduct";
    }

    // Save product:
    @PostMapping("/saveproduct")
    public String save(Product product) {
        prepository.save(product);
        return "redirect:/productlist";
    }

    // Add manufacturer:
    @RequestMapping("/addmanufacturer")
    public String addManufacturer(Model manu) {
        manu.addAttribute("manufacturer", new Manufacturer());
        manu.addAttribute("manufacturers", mrepository.findAll());
        return "addmanufacturer";
    }

    // Save manufacturer:
    @PostMapping("/savemanufacturer")
    public String saveManufacturer(Manufacturer manufacturer) {
        mrepository.save(manufacturer);
        return "redirect:/productlist";
    }
}
