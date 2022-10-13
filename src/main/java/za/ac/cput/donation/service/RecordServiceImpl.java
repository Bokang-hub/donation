package za.ac.cput.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Record;
import za.ac.cput.donation.repositoty.RecordRepository;
import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {
    @Autowired
    RecordRepository repository;

    @Override
    public Record save(Record record) {
        return repository.save(record);
    }
    @Override
    public List<Record> find(long id) {
        return repository.findAllById(id);
    }
    @Override
    public List<Record> findAll() {
        return repository.findAll();
    }
}
