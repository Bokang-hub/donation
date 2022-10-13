package za.ac.cput.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Address;
import za.ac.cput.donation.repositoty.AddressRepository;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService{
    @Autowired
    private AddressRepository repository;

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    public Address update(Address address) {
        return repository.save(address);
    }

    @Override
    public Address findById(long id) {
        return repository.findById(id).orElse(new Address());
    }

    @Override
    public List<Address> findByStreetName(String streetName) {
        return repository.findAllByStreetName(streetName);
    }
}
