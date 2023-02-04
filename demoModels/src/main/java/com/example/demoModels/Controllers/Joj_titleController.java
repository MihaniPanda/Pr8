package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Joj_title;
import com.example.demoModels.Repo.JojTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class Joj_titleController {

    @Autowired
    private JojTitleRepository jojTitleRepository;

    @GetMapping("/jobtitle")
    public String JojTitleMain(Model model)
    {
        Iterable<Joj_title> jojTitle = jojTitleRepository.findAll();
        model.addAttribute("jojTitle",jojTitle);


        return "Joj_title/Joj_title";
    }

    @GetMapping("/jobtitle/add")
    public String JojTitleAddView(@ModelAttribute( "jojTitle") Joj_title joj_title, Model model)
    {

        return "Joj_title/Joj_titleCreate";
    }

    @PostMapping("/jobtitle/add")
    public String jobtitleAdd(@ModelAttribute("jojTitle") @Valid Joj_title joj_title,
                             BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Joj_title/Joj_titleCreate";}
        jojTitleRepository.save(joj_title);
        return "redirect:/jobtitle";
    }

    @PostMapping("/jobtitle/remove/{id}")
    public String deletejobtitledata(@PathVariable(value = "id") long id,Model model)
    {
        Joj_title joj_title = jojTitleRepository.findById(id).orElseThrow();
        jojTitleRepository.delete(joj_title);
        return "redirect:/jobtitle";
    }

    @GetMapping("/jobtitle/edit/{id}")
    public String editjobtitledataPage(@PathVariable(value = "id") long id,Model model)
    {

        Joj_title res = jojTitleRepository.findById(id).orElseThrow();
        model.addAttribute("jojTitles",res);
        if(!jojTitleRepository.existsById(id)){
            return "redirect:/jobtitle";
        }
        return "Joj_title/Joj_titleRedact";
    }

    @PostMapping("/jobtitle/edit/{id}")
    public String editjobtitledata(@PathVariable(value = "id") long id,
                                  @ModelAttribute("jojTitles")  @Valid Joj_title jojTitles, BindingResult bindingResult)

    {
        if(bindingResult.hasErrors()) return "Joj_title/Joj_titleRedact";
        jojTitleRepository.save(jojTitles);
        return "redirect:/jobtitle";
    }
}
