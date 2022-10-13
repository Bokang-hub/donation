package za.ac.cput.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.repositoty.DonorRepository;

@Service
public class DonorServiceImpl implements IDonorService{

    @Autowired
    private DonorRepository repository;

    @Override
    public Donor save(Donor donor) {
        return repository.save(donor);
    }

    @Override
    public Donor login(String email, String password) {
        return repository.findByEmailAndPassword(email, password).orElse(new Donor());
    }

    @Override
    public Donor find(long id) {
        return repository.findById(id).orElse(new Donor());
    }

    @Override
    public boolean delete(long id) {
        Donor delete = this.find(id);
        if(delete != null){
            repository.delete(delete);
            return true;
        }
        return false;
    }

    @Override
    public Donor update(Donor donor) {
        return repository.save(donor);
    }
}
