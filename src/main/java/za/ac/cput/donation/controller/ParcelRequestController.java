package za.ac.cput.donation.controller;

//
//* Author:Lukhona Tetyana
//* Student number: 218119321
//* ADP3 Capstone Project
//*
//

import org.springframework.web.bind.annotation.*;
import za.ac.cput.donation.entity.ParcelRequest;
import za.ac.cput.donation.service.ParcelRequestServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/auth/request")
public class ParcelRequestController {
    private final ParcelRequestServiceImpl service;

    public ParcelRequestController(ParcelRequestServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ParcelRequest save(@RequestBody ParcelRequest parcelRequest) {
        return service.save(parcelRequest);
    }

    @GetMapping("/find/{id}")
    public ParcelRequest find(@PathVariable long id) {
        return service.find(id);
    }

    @GetMapping("find/all")
    public List<ParcelRequest> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable long id) {
        return service.delete(id);
    }
}
