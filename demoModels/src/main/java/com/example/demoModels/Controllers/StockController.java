package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Certificate;
import com.example.demoModels.Models.Stock;
import com.example.demoModels.Repo.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/stock")
    public String StockeMain(Model model)
    {
        Iterable<Stock> stock = stockRepository.findAll();
        model.addAttribute("stockk",stock);
        return "Stock/Stock";
    }

    @GetMapping("/stock/add")
    public String StockAddView(@ModelAttribute( "stockk") Stock stock, Model model)
    {
        return "Stock/StockCreate";
    }


    @PostMapping("/stock/add")
    public String StockAdd(@ModelAttribute("stockk") @Valid Stock stock,
                                 BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Stock/StockCreate";}
        stockRepository.save(stock);
        return "redirect:/stock";
    }

    @PostMapping("/stock/remove/{id}")
    public String deleteStockdata(@PathVariable(value = "id") long id,Model model)
    {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stockRepository.delete(stock);
        return "redirect:/stock";
    }

    @GetMapping("/stock/edit/{id}")
    public String editStockdataPage(@PathVariable(value = "id") long id,Model model)
    {
        Stock res = stockRepository.findById(id).orElseThrow();
        model.addAttribute("stocke",res);
        if(!stockRepository.existsById(id)){
            return "redirect:/stock";
        }
        return "Stock/StockRedact";
    }

    @PostMapping("/stock/edit/{id}")
    public String editStockdata(@PathVariable(value = "id") long id,
                                      @ModelAttribute("stocke")  @Valid Stock stocke, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "Stock/StockRedact";
        stockRepository.save(stocke);
        return "redirect:/stock";
    }
}
