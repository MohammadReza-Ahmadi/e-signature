package com.vsq.esignature.controller;

import com.vsq.esignature.model.Person;
import com.vsq.esignature.service.DocumentService;
import com.vsq.esignature.service.PersonService;
import org.springframework.web.bind.annotation.*;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;
    private DocumentService documentService;

    public PersonController(PersonService personService, DocumentService documentService) {
        this.personService = personService;
        this.documentService = documentService;
    }

    @GetMapping("/get-person/{personId}")
    public Person getPerson(@PathVariable Long personId) {
        return personService.get(personId);
    }

    @PostMapping("/add-person")
    public Person addPerson(@RequestBody Person person) {
        return personService.insert(person);
    }
}
