package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Joj_title;
import com.example.demoModels.Models.Passport;
import com.example.demoModels.Repo.JojTitleRepository;
import com.example.demoModels.Repo.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class PassportController {

    @Autowired
    private PassportRepository passportRepository;

    @GetMapping("/passport")
    public String PassportMain(Model model)
    {
        Iterable<Passport> passport = passportRepository.findAll();
        model.addAttribute("passporte",passport);


        return "Passport/Passport";
    }

    @GetMapping("/passport/add")
    public String PassportAddView(@ModelAttribute( "passporte") Passport passport, Model model)
    {

        return "Passport/PassportCreate";
    }

    @PostMapping("/passport/add")
    public String PassportAdd(@ModelAttribute("passporte") @Valid Passport passport,
                              BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Passport/PassportCreate";}
        passportRepository.save(passport);
        return "redirect:/passport";
    }

    @PostMapping("/passport/remove/{id}")
    public String deletePassportdata(@PathVariable(value = "id") long id,Model model)
    {
        Passport passport = passportRepository.findById(id).orElseThrow();
        passportRepository.delete(passport);
        return "redirect:/passport";
    }

    @GetMapping("/passport/edit/{id}")
    public String editPassportdataPage(@PathVariable(value = "id") long id,Model model)
    {

        Passport res = passportRepository.findById(id).orElseThrow();
        model.addAttribute("passports",res);
        if(!passportRepository.existsById(id)){
            return "redirect:/passport";
        }
        return "Passport/PassportRedact";
    }

    @PostMapping("/passport/edit/{id}")
    public String editPassportdata(@PathVariable(value = "id") long id,
                                   @ModelAttribute("passports")  @Valid Passport passports, BindingResult bindingResult)

    {
        if(bindingResult.hasErrors()) return "Passport/PassportRedact";
        passportRepository.save(passports);
        return "redirect:/passport";
    }
}
