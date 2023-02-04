package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Proizvoditel;
import com.example.demoModels.Models.Type;
import com.example.demoModels.Repo.ProizvoditelRepository;
import com.example.demoModels.Repo.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class ProizvoditelController {

    @Autowired
    private ProizvoditelRepository proizvoditelRepository;

    @GetMapping("/proizvoditel")
    public String ProizvoditelMain(Model model)
    {
        Iterable<Proizvoditel> proizvoditel = proizvoditelRepository.findAll();
        model.addAttribute("proizvoditele",proizvoditel);
        return "Proizvoditel/Proizvoditel";
    }

    @GetMapping("/proizvoditel/add")
    public String ProizvoditelAddView(@ModelAttribute( "proizvoditele") Proizvoditel proizvoditel, Model model)
    {
        return "Proizvoditel/ProizvoditelCreate";
    }

    @PostMapping("/proizvoditel/add")
    public String ProizvoditelAdd(@ModelAttribute("proizvoditele") @Valid Proizvoditel proizvoditel,
                          BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Proizvoditel/ProizvoditelCreate";}
        proizvoditelRepository.save(proizvoditel);
        return "redirect:/proizvoditel";
    }

    @PostMapping("/proizvoditel/remove/{id}")
    public String deleteProizvoditeldata(@PathVariable(value = "id") long id, Model model)
    {
        Proizvoditel proizvoditel = proizvoditelRepository.findById(id).orElseThrow();
        proizvoditelRepository.delete(proizvoditel);
        return "redirect:/proizvoditel";
    }

    @GetMapping("/proizvoditel/edit/{id}")
    public String editProizvoditeldataPage(@PathVariable(value = "id") long id,Model model)
    {
        Proizvoditel res = proizvoditelRepository.findById(id).orElseThrow();
        model.addAttribute("proizvoditels",res);
        if(!proizvoditelRepository.existsById(id)){
            return "redirect:/proizvoditel";
        }
        return "Proizvoditel/ProizvoditelRedact";
    }

    @PostMapping("/proizvoditel/edit/{id}")
    public String editProizvoditeldata(@PathVariable(value = "id") long id,
                               @ModelAttribute("proizvoditels")  @Valid Proizvoditel proizvoditels, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "Proizvoditel/ProizvoditelRedact";
        proizvoditelRepository.save(proizvoditels);
        return "redirect:/proizvoditel";
    }
}
