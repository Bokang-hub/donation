package za.ac.cput.donation.controller;
//
//* Author:Lukhona Tetyana
//* Student number: 218119321
//* ADP3 Capstone Project
//*
//
import org.springframework.web.bind.annotation.*;
import za.ac.cput.donation.entity.Parcel;
import za.ac.cput.donation.service.ParcelServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/auth/parcel")
public class ParcelController {
    private final ParcelServiceImpl service;

    public ParcelController(ParcelServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Parcel save(@RequestBody Parcel parcel) {
        return service.save(parcel);
    }

    @GetMapping("/find/{id}")
    public Parcel find(@PathVariable long id) {
        return service.find(id);
    }

    @GetMapping("/find/all")
    public List<Parcel> findAll() {
        return service.findAll();
    }

    @PostMapping("/update")
    public Parcel update(@RequestBody Parcel parcel) {
        return service.update(parcel);
    }
}
