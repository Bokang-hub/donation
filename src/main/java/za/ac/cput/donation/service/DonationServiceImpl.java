package za.ac.cput.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Donation;
import za.ac.cput.donation.repositoty.DonationRepository;

import java.util.List;

@Service
public class DonationServiceImpl implements IDonationService{
    @Autowired
    private DonationRepository repository;

    @Override
    public Donation save(Donation donation) {
        return repository.save(donation);
    }

    @Override
    public List<Donation> findByDonor(long donorId) {
        return repository.findAllByDonorId(donorId);
    }

    @Override
    public List<Donation> findByType(String donationType) {
        return repository.findAllByDonationType(donationType);
    }
}
