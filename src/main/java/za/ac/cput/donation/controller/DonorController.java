package za.ac.cput.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.service.DonorServiceImpl;

@RestController
@RequestMapping("/auth/donor")
public class DonorController {

    @Autowired
    DonorServiceImpl service;

    @GetMapping("/find/{id}")
    public Donor find(@PathVariable long id) {
        return service.find(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable long id) {
        return service.delete(id);
    }

    @PostMapping("/update")
    public Donor update(@RequestBody Donor donor) {
        return service.update(donor);
    }
}
