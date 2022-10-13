package za.ac.cput.donation.service;

import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.ParcelRequest;
import za.ac.cput.donation.repositoty.ParcelRequestRepository;

import java.util.List;

@Service
public class ParcelRequestServiceImpl implements IParcelRequestService{

    private final ParcelRequestRepository repository;

    public ParcelRequestServiceImpl(ParcelRequestRepository repository){
        this.repository = repository;
    }

    @Override
    public ParcelRequest save(ParcelRequest parcelRequest) {
        return repository.save(parcelRequest);
    }

    @Override
    public ParcelRequest find(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ParcelRequest> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ParcelRequest> findAllByUser(long id) {
        return repository.findAllByStudentId(id);
    }

    @Override
    public List<ParcelRequest> findAllPending() {
        return repository.findAllByIsReceivedFalse();
    }

    @Override
    public boolean delete(long id) {
        ParcelRequest p = this.find(id);
        if(p != null){
            repository.delete(p);
            return true;
        }
        return false;
    }
}
