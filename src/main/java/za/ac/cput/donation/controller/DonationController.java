package za.ac.cput.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.donation.entity.Donation;
import za.ac.cput.donation.service.DonationServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/auth/donation")
public class DonationController {
    @Autowired
    private DonationServiceImpl service;

    @PostMapping("/save")
    public Donation save(@RequestBody Donation donation) {
        return service.save(donation);
    }

    @GetMapping("/find-by/donor/{donorId}")
    public List<Donation> findByDonor(@PathVariable long donorId) {
        return service.findByDonor(donorId);
    }

    @GetMapping("/find-by/type/{donationType}")
    public List<Donation> findByType(@PathVariable String donationType) {
        return service.findByType(donationType);
    }
}
