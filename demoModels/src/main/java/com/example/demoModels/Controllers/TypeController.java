package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Stock;
import com.example.demoModels.Models.Type;
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
public class TypeController {
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/type")
    public String TypeMain(Model model)
    {
        Iterable<Type> type = typeRepository.findAll();
        model.addAttribute("typee",type);
        return "Type/Type";
    }

    @GetMapping("/type/add")
    public String TypeAddView(@ModelAttribute( "typee") Type type, Model model)
    {
        return "Type/TypeCreate";
    }

    @PostMapping("/type/add")
    public String TypeAdd(@ModelAttribute("typee") @Valid Type type,
                           BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Type/TypeCreate";}
        typeRepository.save(type);
        return "redirect:/type";
    }

    @PostMapping("/type/remove/{id}")
    public String deleteTypedata(@PathVariable(value = "id") long id, Model model)
    {
        Type type = typeRepository.findById(id).orElseThrow();
        typeRepository.delete(type);
        return "redirect:/type";
    }

    @GetMapping("/type/edit/{id}")
    public String editTypedataPage(@PathVariable(value = "id") long id,Model model)
    {
        Type res = typeRepository.findById(id).orElseThrow();
        model.addAttribute("types",res);
        if(!typeRepository.existsById(id)){
            return "redirect:/type";
        }
        return "Type/TypeRedact";
    }

    @PostMapping("/type/edit/{id}")
    public String editTypedata(@PathVariable(value = "id") long id,
                                @ModelAttribute("types")  @Valid Type types, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "Type/TypeRedact";
        typeRepository.save(types);
        return "redirect:/type";
    }
}
