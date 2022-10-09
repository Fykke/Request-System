package com.maksim.requestsystem.controllers;

// all needed imports
import com.maksim.requestsystem.models.Requests;
import com.maksim.requestsystem.repositories.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller // tells compiler and spring boot that this class is a controller
public class MainController { // Controller to track all urls and connect model with view (html pages)


    @Autowired
    private RequestsRepository requestsRepository;  // variable to work with database table

    @GetMapping("/requests")   // tracking url localhost...:/requests
    public String request(Model model) {
        Iterable<Requests> requests = requestsRepository.findAll(); // finding every object in the table
        model.addAttribute("requests", requests); // adding objects(attributes) to the model, giving them name "requests" and sending to the home.html
        return "home"; // calling home.html (view)
    }

    @GetMapping("/newrequest")    // page for sending a new request.   Url is connected to newRequest.html. no data is transferred
    public String newRequest(Model model) {
        return "newRequest";
    }

    @PostMapping("/newrequest")   // post method for the same page. Transferring data from fields that were filled by user to the database.
    public String newRequestPosting(@RequestParam String component, @RequestParam String name, @RequestParam String addition, Model model) { //asking for parameters from fields in html page
        Requests request = new Requests(component, name, addition, LocalDateTime.now().toString(), false); // creating a table object (entity) with given arguments
        requestsRepository.save(request); // saving given object to the database using requestsRepository (extended by CrudRepository)
        return "redirect:/requests"; // sending user to the main page
    }

    @GetMapping("/requests/{id}") // url to track individual request (item/object in database)
    public String requestById(@PathVariable(value="id") long id, Model model) { // extract data (id) from the URI
        if(!requestsRepository.existsById(id)) { // if object with given id is not existent -> redirect to the main page
            return "redirect:/requests";
        }

        Optional<Requests> request = requestsRepository.findById(id); // if object exists -> finding it in DB by id (optional is used to use possible null values)
        ArrayList<Requests> res = new ArrayList<>(); // transferring optional to arraylist
        request.ifPresent(res::add);
        model.addAttribute("request", res); // transferring data to the view through the "request" variable
        return "requestDetails"; // calling requestDetails.html
    }

    @PostMapping("/requests/{id}/remove")
    public String requestDelete(@PathVariable(value="id") long id, Model model) {    // method used to remove an item from database
        Requests request = requestsRepository.findById(id).orElseThrow(); // orElseThrow to prevent some errors
        requestsRepository.delete(request); // deleting an object from DB
        return "redirect:/requests";
    }

    @PostMapping("/requests/{id}/update")
    public String requestUpdate(@PathVariable(value="id") long id, Model model) { // method used to update an object
        Requests request = requestsRepository.findById(id).orElseThrow(); // finding an object by its id
        request.setRequestDone(!request.isRequestDone());  // setting boolean value to opposite
        requestsRepository.save(request); // saving updates
        return "redirect:/requests";
    }
}