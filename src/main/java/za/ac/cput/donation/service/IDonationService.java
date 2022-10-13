package za.ac.cput.donation.service;

import za.ac.cput.donation.entity.Donation;

import java.util.List;

public interface IDonationService {
    Donation save(Donation donation);
    List<Donation> findByDonor(long donorId);
    List<Donation> findByType(String donationType);
}
