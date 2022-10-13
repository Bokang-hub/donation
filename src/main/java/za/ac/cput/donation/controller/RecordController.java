package za.ac.cput.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.donation.entity.ParcelRequest;
import za.ac.cput.donation.entity.Record;
import za.ac.cput.donation.service.IRecordService;
import za.ac.cput.donation.service.ParcelRequestServiceImpl;
import za.ac.cput.donation.service.RecordServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordServiceImpl service;

    @Autowired
    ParcelRequestServiceImpl parcelRequestService;

    @PostMapping("/save")
    public Record save(@RequestBody Record record) {
        return service.save(record);
    }
    @GetMapping("/find-by/{id}")
    public List<Record> find(@PathVariable long id) {
        List<ParcelRequest> pr = parcelRequestService.findAllByUser(id);
        List<Record> r = new ArrayList<>();
        for(int i = 0; i < pr.size(); i++){
            r.addAll(service.find(pr.get(i).getId()));
        }
        return r;
    }
    @GetMapping("/find/all")
    public List<Record> findAll() {
        return service.findAll();
    }
}
