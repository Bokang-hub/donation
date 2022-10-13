package za.ac.cput.donation.service;

import za.ac.cput.donation.entity.Record;
import java.util.List;

public interface IRecordService {
    Record save(Record record);
    List<Record> find(long id);
    List<Record> findAll();
}
