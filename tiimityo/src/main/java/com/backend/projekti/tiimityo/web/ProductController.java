package com.backend.projekti.tiimityo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.backend.projekti.tiimityo.domain.AppUser;
import com.backend.projekti.tiimityo.domain.AppUserRepository;
import com.backend.projekti.tiimityo.domain.Manufacturer;
import com.backend.projekti.tiimityo.domain.ManufacturerRepository;
import com.backend.projekti.tiimityo.domain.Product;
import com.backend.projekti.tiimityo.domain.ProductRepository;
import com.backend.projekti.tiimityo.domain.ProductType;
import com.backend.projekti.tiimityo.domain.ProductTypeRepository;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository prepository;
    @Autowired
    private ManufacturerRepository mrepository;
    @Autowired
    private ProductTypeRepository trepository;
    @Autowired
    private AppUserRepository appUserRepository;

    // Get all products:
    @GetMapping("/productlist")
    public String getProductList(Model model) {
        model.addAttribute("products", prepository.findAll());
        model.addAttribute("manufacturers", mrepository.findAll());
        model.addAttribute("productTypes", trepository.findAll());
        return "productlist";
    }

    // Frontpage
    @GetMapping("/frontpage")
    public String getFrontPage(Model model) {
        return "frontpage";
    }

    // Edit product:
    @RequestMapping("edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        model.addAttribute("product", prepository.findById(productId));
        model.addAttribute("manufacturers", mrepository.findAll());
        model.addAttribute("productTypes", trepository.findAll());
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

    // Delete manufacturer:
    @GetMapping("/deletemanufacturer/{id}")
    public String deleteManufacturer(@PathVariable Long id, RedirectAttributes redirAttrs) {
        Manufacturer manufacturer = mrepository.findById(id).orElseThrow(
                () -> new RuntimeException("Manufacturer not found"));

        if (manufacturer.getProducts().size() > 0) {
            redirAttrs.addFlashAttribute("error", "Valmistajalla tuotteita järjestelmässä. Poistaminen ei mahdollista.");

            return "redirect:/manufacturerlist";
        }

        mrepository.deleteById(id);
        return "redirect:/manufacturerlist";
    }

    // Save manufacturer:
    @PostMapping("/savemanufacturer")
    public String saveManufacturer(@Valid Manufacturer manufacturer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("manufacturers", mrepository.findAll());
            return "addmanufacturer";
        }
        mrepository.save(manufacturer);
        return "redirect:/manufacturerlist";
    }

    // Get all manufacturers:
    @GetMapping("/manufacturerlist")
    public String getManufacturerList(Model model) {
        model.addAttribute("manufacturers", mrepository.findAll());
        return "manufacturerlist";
    }

    // See manufacturer's products:
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
        model.addAttribute("productTypes", trepository.findAll());
        return "addproduct";
    }

    // List the number of products:
    @GetMapping("/stocklist")
    public String listNumberOfProducts(Model model) {
        model.addAttribute("products", prepository.findAll());
        return "stocklist";
    }

    // Cancel action:
    @GetMapping("/cancelaction")
    public String cancelAction(Model model) {
        return "redirect:/productlist";
    }

    // Save product:
    // This method is used to save a new product to the database.
    @PostMapping("/saveproduct")
    public String saveProduct(@RequestParam String title,
            @RequestParam double price,
            @RequestParam Long productTypeId,
            @RequestParam String color,
            @RequestParam String size,
            @RequestParam Long manufacturerId,
            @RequestParam int quantity,
            Model model) {

        boolean hasErrors = false;

        if (title == null || title.trim().isEmpty()) {
            model.addAttribute("errorTitle", "Tuotteen nimi on pakollinen");
            hasErrors = true;
        }
        if (price <= 0) {
            model.addAttribute("errorPrice", "Hinnan on oltava suurempi kuin 0");
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("productTypes", trepository.findAll());
            model.addAttribute("manufacturers", mrepository.findAll());
            return "addproduct";
        }

        Manufacturer manufacturer = mrepository.findById(manufacturerId).orElse(null);
        ProductType productType = trepository.findById(productTypeId).orElse(null);

        Product product = new Product(title, price, productType, color, size, manufacturer, quantity);
        prepository.save(product);

        return "redirect:/productlist";
    }

    @GetMapping(value = {"resetDB"})

    public String resetDB() {

        // Deletes all products and manufacturers from the database
        prepository.deleteAll();

        mrepository.deleteAll();

        // test data
        Manufacturer rukka = new Manufacturer("Rukka");
        mrepository.save(rukka);
        Manufacturer pomppa = new Manufacturer("Pomppa");
        mrepository.save(pomppa);
        Manufacturer feelActive = new Manufacturer("Feel Active");
        mrepository.save(feelActive);

        ProductType vaate = new ProductType("Vaate");
        trepository.save(vaate);
        ProductType ruoka = new ProductType("Ruoka");
        trepository.save(ruoka);
        ProductType lelu = new ProductType("Lelu");
        trepository.save(lelu);

        prepository.save(new Product("Talvitakki", 53.90, vaate, "Violetti", "M", rukka, 11));
        prepository.save(new Product("Sadetakki", 44.90, vaate, "Keltainen", "L", pomppa, 3));
        prepository.save(new Product("Neule", 21.99, vaate, "Vihreä", "S", feelActive, 7));

        AppUser existingUser = appUserRepository.findByUsername("adminn");

        if (existingUser != null) {
            return "redirect:/frontpage";
        }

        AppUser user1 = new AppUser("adminn", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
                "ADMIN");
        appUserRepository.save(user1);
        return "redirect:/frontpage";
    }
}
