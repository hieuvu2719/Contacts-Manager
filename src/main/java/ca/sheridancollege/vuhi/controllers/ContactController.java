package ca.sheridancollege.vuhi.controllers;

import ca.sheridancollege.vuhi.beans.Contact;
import ca.sheridancollege.vuhi.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private DatabaseAccess da;

    @GetMapping
    public List<Contact> getAllContact(){
        return da.getAllContact();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id){
        return da.getContactById(id).get(0);
    }

    @PostMapping(consumes = "application/json")
    public Long postContact(@RequestBody Contact contact){
        return da.insertContact(contact);
    }


    @PutMapping(consumes = "application/json")
    public String putContactIndividual(@RequestBody Contact contact){
        da.updateContact(contact);
        return "Updated contact with id" + contact.getId();
    }

    @DeleteMapping("/{id}")
    public void deleteContactIndividual(@PathVariable Long id){
        da.deleteContact(id);
    }

}
