package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Certificate;
import com.example.demoModels.Repo.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")

public class CertificateController {

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping("/certificate")
    public String СertificateMain(Model model)
    {
        Iterable<Certificate> сertificate = certificateRepository.findAll();
        model.addAttribute("certificatee",сertificate);
        return "Certificate/Certificate";
    }

    @GetMapping("/certificate/add")
    public String CertificateAddView(@ModelAttribute( "certificatee") Certificate certificate, Model model)
    {
        return "Certificate/CertificateCreate";
    }

    @PostMapping("/certificate/add")
    public String CertificateAdd(@ModelAttribute("certificatee") @Valid Certificate certificate,
                              BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Certificate/CertificateCreate";}
        certificateRepository.save(certificate);
        return "redirect:/certificate";
    }

    @PostMapping("/certificate/remove/{id}")
    public String deleteCertificatedata(@PathVariable(value = "id") long id,Model model)
    {
        Certificate certificate = certificateRepository.findById(id).orElseThrow();
        certificateRepository.delete(certificate);
        return "redirect:/certificate";
    }

    @GetMapping("/certificate/edit/{id}")
    public String editCertificatedataPage(@PathVariable(value = "id") long id,Model model)
    {
        Certificate res = certificateRepository.findById(id).orElseThrow();
        model.addAttribute("certificates",res);
        if(!certificateRepository.existsById(id)){
            return "redirect:/certificate";
        }
        return "Certificate/CertificateRedact";
    }

    @PostMapping("/certificate/edit/{id}")
    public String editCertificatedata(@PathVariable(value = "id") long id,
                                   @ModelAttribute("certificates")  @Valid Certificate certificates, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "Certificate/CertificateRedact";
        certificateRepository.save(certificates);
        return "redirect:/certificate";
    }
}
