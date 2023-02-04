package com.example.demoModels.Controllers;


import com.example.demoModels.Models.*;
import com.example.demoModels.Repo.EmployeeRepository;
import com.example.demoModels.Repo.JojTitleRepository;
import com.example.demoModels.Repo.PassportRepository;
import com.example.demoModels.Repo.UserRepository;
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
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JojTitleRepository jojTitleRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/employee")
    public String EmployeeMain(Model model)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employers",employees);

        Iterable<Joj_title> jojTitle = jojTitleRepository.findAll();
        model.addAttribute("jojTitle",jojTitle);

        Iterable<Passport> passports = passportRepository.findAll();
        model.addAttribute("passports",passports);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "Employee/Employee";
    }

    @GetMapping("/employee/add")
    public String employeeAddView(@ModelAttribute( "employers") Employee employee, Model model)
    {
        Iterable<Passport> passports = passportRepository.findAll();
        ArrayList<Passport> passportArrayList = new ArrayList<>();
        for (Passport pass: passports){
            if(pass.getEmployee()==null) {
                passportArrayList.add(pass);
            }
        }
        model.addAttribute("passports", passportArrayList);

        Iterable<Joj_title> jojTitle = jojTitleRepository.findAll();
        model.addAttribute("jojTitle",jojTitle);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);

        return "Employee/EmployeeCreate";
    }

    @PostMapping("/employee/add")
    public String employeeAdd(@ModelAttribute("employers") @Valid Employee employee, BindingResult bindingResuit,
                              @RequestParam String number, String username, String namejob, Model model)
    {

        if(bindingResuit.hasErrors())
        {
            Iterable<Joj_title> jojTitle = jojTitleRepository.findAll();
            model.addAttribute("jojTitle",jojTitle);

            Iterable<Passport> passports = passportRepository.findAll();
            model.addAttribute("passports",passports);

            Iterable<User> users = userRepository.findAll();
            model.addAttribute("users",users);
            return "Employee/EmployeeCreate";
        }
        employee.setPassport(passportRepository.findByNumber(number));
        employee.setUser(userRepository.findByUsername(username));
        employee.setJoj_title(jojTitleRepository.findByNamejob(namejob));
        employeeRepository.save(employee);
        return "redirect:/employee";
    }


    @PostMapping("/employee/remove/{id}")
    public String deleteEmployeedata(@PathVariable(value = "id") long id,Model model)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }


    @GetMapping("/employee/edit/{id}")
    public String editEmployeedataPage(@PathVariable(value = "id") long id,Model model)
    {
        Iterable<Joj_title> jojTitle = jojTitleRepository.findAll();
        Iterable<Passport> passports = passportRepository.findAll();
        Iterable<User> users = userRepository.findAll();

        Employee employee = employeeRepository.findById(id).orElseThrow(()
        ->new IllegalArgumentException("Invalid students Id" + id));

        model.addAttribute("jojTitle",jojTitle);
        model.addAttribute("passports",passports);
        model.addAttribute("users",users);
        model.addAttribute("employee", employee);
        return "Employee/EmployeeRedact";
    }

    @PostMapping("/employee/edit/{id}")
    public String editEmployeedata(@ModelAttribute("employee")  @Valid Employee employee, BindingResult bindingResult,
                                  @RequestParam String number,@RequestParam String username,@RequestParam String namejob,
                                  @PathVariable(value = "id") long id) {
        employee.setId(id);
        if(bindingResult.hasErrors()){
            Iterable<Joj_title> jojTitle = jojTitleRepository.findAll();
            Iterable<Passport> passports = passportRepository.findAll();
            Iterable<User> users = userRepository.findAll();
            return "Employee/EmployeeRedact";
        }

        var passport = passportRepository.findByNumber(number);
        passport.setEmployee(null);
        employee.setPassport(passport);

        var user = userRepository.findByUsername(username);
        user.setEmployee(null);
        employee.setUser(user);

        var job = jojTitleRepository.findByNamejob(namejob);
        employee.setJoj_title(job);

        employeeRepository.save(employee);
        return "redirect:/employee";
    }
}