package com.example.demoModels.Controllers;



import com.example.demoModels.Models.*;
import com.example.demoModels.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProizvoditelRepository proizvoditelRepository;

    @GetMapping("/product")
    public String ProductMain(Model model)
    {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);

        Iterable<Type> types = typeRepository.findAll();
        model.addAttribute("types",types);

        Iterable<Proizvoditel> proizvoditels = proizvoditelRepository.findAll();
        model.addAttribute("proizvoditels",proizvoditels);

        return "Product/Product";
    }

    @GetMapping("/product/add")
    public String ProductAddView(@ModelAttribute( "products") Product product, Model model)
    {
        Iterable<Proizvoditel> proizvoditels = proizvoditelRepository.findAll();
        model.addAttribute("proizvoditels", proizvoditels);

        Iterable<Type> types = typeRepository.findAll();
        model.addAttribute("types",types);

        return "Product/ProductCreate";
    }

    @PostMapping("/product/add")
    public String ProductAdd(@ModelAttribute("products") @Valid Product product, BindingResult bindingResuit)
    {

        if(bindingResuit.hasErrors())
        {

            return "Product/ProductCreate";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @PostMapping("/product/remove/{id}")
    public String deleteProductdata(@PathVariable(value = "id") long id,Model model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/product";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductdataPage(@PathVariable(value = "id") long id,Model model)
    {

        Product res = productRepository.findById(id).orElseThrow();
        model.addAttribute("producte",res);
        if(!productRepository.existsById(id)){
            return "redirect:/product";
        }
        return "Product/ProductRedact";
    }

    @PostMapping("/product/edit/{id}")
    public String editProductdata(@PathVariable(value = "id") long id,
                                   @ModelAttribute("producte")  @Valid Product product, BindingResult bindingResult)

    {
        if(bindingResult.hasErrors()) return "Product/ProductRedact";
        productRepository.save(product);
        return "redirect:/product";
    }
}
