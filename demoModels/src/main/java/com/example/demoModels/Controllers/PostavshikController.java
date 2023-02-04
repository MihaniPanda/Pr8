package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Postavshik;
import com.example.demoModels.Models.Proizvoditel;
import com.example.demoModels.Models.Type;
import com.example.demoModels.Repo.PostavshikRepository;
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

public class PostavshikController {

    @Autowired
    private PostavshikRepository postavshikRepository;

    @GetMapping("/postavshik")
    public String PostavshikMain(Model model)
    {
        Iterable<Postavshik> postavshike = postavshikRepository.findAll();
        model.addAttribute("postavshike",postavshike);
        return "Postavshik/Postavshik";
    }

    @GetMapping("/postavshik/add")
    public String PostavshikAddView(@ModelAttribute( "postavshike") Postavshik postavshik, Model model)
    {
        return "Postavshik/PostavshikCreate";
    }

    @PostMapping("/postavshik/add")
    public String PostavshikAdd(@ModelAttribute("postavshike") @Valid Postavshik postavshik,
                                  BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Postavshik/PostavshikCreate";}
        postavshikRepository.save(postavshik);
        return "redirect:/postavshik";
    }

    @PostMapping("/postavshik/remove/{id}")
    public String deletePostavshikdata(@PathVariable(value = "id") long id, Model model)
    {
        Postavshik postavshik = postavshikRepository.findById(id).orElseThrow();
        postavshikRepository.delete(postavshik);
        return "redirect:/postavshik";
    }

    @GetMapping("/postavshik/edit/{id}")
    public String editPostavshikdataPage(@PathVariable(value = "id") long id,Model model)
    {
        Postavshik res = postavshikRepository.findById(id).orElseThrow();
        model.addAttribute("postavshiks",res);
        if(!postavshikRepository.existsById(id)){
            return "redirect:/postavshik";
        }
        return "Postavshik/PostavshikRedact";
    }

    @PostMapping("/postavshik/edit/{id}")
    public String editPostavshikdata(@PathVariable(value = "id") long id,
                                       @ModelAttribute("postavshiks")  @Valid Postavshik postavshiks, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "Postavshik/PostavshikRedact";
        postavshikRepository.save(postavshiks);
        return "redirect:/postavshik";
    }
}
