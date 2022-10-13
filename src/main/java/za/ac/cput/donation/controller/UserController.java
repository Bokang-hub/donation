package za.ac.cput.donation.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.donation.entity.Address;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.entity.Student;
import za.ac.cput.donation.entity.User;
import za.ac.cput.donation.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;
    private DonorServiceImpl donorService;
    private StudentServiceImpl studentService;
    private AddressServiceImpl addressService;

    public UserController(UserServiceImpl userService, DonorServiceImpl donorService, StudentServiceImpl studentService, AddressServiceImpl addressService){
        this.userService = userService;
        this.donorService = donorService;
        this.studentService = studentService;
        this.addressService = addressService;
    }

    @GetMapping("/find/{id}")
    public User find(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping("/login/donor/{email}/{password}")
    public Donor loginDonor(@PathVariable String email, @PathVariable String password) {
        return donorService.login(email, password);
    }

    @GetMapping("/login/student/{email}/{password}")
    public Student login(@PathVariable String email, @PathVariable String password) {
        return studentService.login(email, password);
    }

    @PostMapping("/save/donor")
    public Donor save(@RequestBody Donor donor) {
        return donorService.save(donor);
    }

    @PostMapping("/save/student")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/save/address")
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }

}
