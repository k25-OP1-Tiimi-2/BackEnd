package com.backend.projekti.tiimityo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // Saves the product in editproduct.html:
    @PostMapping("/save")
    public String save(Product product) {
        prepository.save(product);
        return "redirect:/productlist";
    }

    // Delete product:
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        prepository.deleteById(id);
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

    // Go to manufacturer's page:
    @GetMapping("/manufacturer/{id}")
    public String getManufacturer(@PathVariable Long id, Model model) {
        Manufacturer manufacturer = mrepository.findById(id).orElse(null);
        List<Product> products = prepository.findByManufacturer(manufacturer);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("products", products);
        return "manufacturerpage";
    }

    // Add product:
    @RequestMapping("/addproduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("manufacturers", mrepository.findAll());
        return "addproduct";
    }

    // Save product:
    // This method is used to save a new product to the database.
    @PostMapping("/saveproduct")
    public String saveProduct(@RequestParam String title,
            @RequestParam double price,
            @RequestParam String type,
            @RequestParam String color,
            @RequestParam String size,
            @RequestParam Long manufacturerId) {

        Manufacturer manufacturer = mrepository.findById(manufacturerId).orElse(null);

        Product product = new Product(title, price, type, color, size, manufacturer);
        prepository.save(product); // Saves the product to the database
        return "redirect:/productlist";
    }

}
