package ca.sheridancollege.vuhi.controllers;

import ca.sheridancollege.vuhi.beans.Contact;
import ca.sheridancollege.vuhi.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    @Lazy
    private DatabaseAccess da;

    @GetMapping("/")
    public String index(Model model){
        return "home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }

    @GetMapping("/permission_denied")
    public String noPermission(){

        return "/error/denied";
    }

    @GetMapping("/deleteContactById/{id}")
    public String delete(@PathVariable Long id, Model model, RestTemplate restTemplate){
        restTemplate.delete(URI.create("http://localhost:8080/contacts/"+ id));
        ResponseEntity<Contact[]> responseEntity = restTemplate.getForEntity
                ("http://localhost:8080/contacts",Contact[].class);
        model.addAttribute("contactList",responseEntity.getBody());
        return "/secure/listContacts";
    }

    @GetMapping("/editContactById/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("contact",da.getContactById(id).get(0));
        return "/secure/updateContact";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Contact contact, Model model, RestTemplate restTemplate){
        restTemplate.put("http://localhost:8080/contacts/",contact);
        ResponseEntity<Contact[]> responseEntity = restTemplate.getForEntity
                ("http://localhost:8080/contacts",Contact[].class);
        model.addAttribute("contactList",responseEntity.getBody());
        return "/secure/listContacts";
    }

    @GetMapping("/secure/listContacts")
    public String listContact(Model model, RestTemplate restTemplate){
        ResponseEntity<Contact[]> responseEntity = restTemplate.getForEntity
                ("http://localhost:8080/contacts",Contact[].class);
        model.addAttribute("contactList",responseEntity.getBody());
        return "/secure/listContacts";
    }


    @PostMapping("/insertContact")
    public String insertContact(Model model, @ModelAttribute Contact contact, RestTemplate restTemplate){
        ResponseEntity<Contact> responseEntity = restTemplate.postForEntity
                ("http://localhost:8080/contacts/", contact, Contact.class);
        model.addAttribute("contact", new Contact());
        ResponseEntity<Contact[]> responseEntity2 = restTemplate.getForEntity
                ("http://localhost:8080/contacts",Contact[].class);
        model.addAttribute("contactList",responseEntity2.getBody());
        return "/secure/listContacts";
    }

    @GetMapping("/secure/addContact")
    public String addContact(Model model){
        model.addAttribute("contact",new Contact());
        return "/secure/addContact";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam List<String> roles) {
        da.addUser(username, password);
        Long userId= da.findUserAccount(username).getUserId();
        for (String role: roles) {
            da.addRole(userId, Long.parseLong(role));
        }
        return "home";
    }
}
