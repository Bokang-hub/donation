package za.ac.cput.donation.service;

import za.ac.cput.donation.entity.Donor;

public interface IDonorService {
    Donor save(Donor donor);
    Donor find(long id);
    boolean delete(long id);
    Donor update(Donor donor);

    Donor login(String email, String password);
}
