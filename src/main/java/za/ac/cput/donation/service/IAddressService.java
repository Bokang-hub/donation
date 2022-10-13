package za.ac.cput.donation.service;

import za.ac.cput.donation.entity.Address;

import java.util.List;

public interface IAddressService {
    Address save(Address address);
    Address update(Address address);
    Address findById(long id);
    List<Address> findByStreetName(String streetName);
}
