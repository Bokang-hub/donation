package za.ac.cput.donation.service;

import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Parcel;
import za.ac.cput.donation.repositoty.ParcelRepository;

import java.util.List;
@Service
public class ParcelServiceImpl implements IParcelService{
    private final ParcelRepository repository;

    public ParcelServiceImpl(ParcelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Parcel save(Parcel parcel) {
        return repository.save(parcel);
    }

    @Override
    public Parcel find(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Parcel> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Parcel> findAllAvailable() {
        return repository.findAllByStatusTrue();
    }

    @Override
    public Parcel update(Parcel parcel) {
        return repository.save(parcel);
    }
}
