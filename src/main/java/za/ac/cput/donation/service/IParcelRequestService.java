package za.ac.cput.donation.service;

import za.ac.cput.donation.entity.ParcelRequest;

import java.util.List;

public interface IParcelRequestService {
    ParcelRequest save(ParcelRequest parcelRequest);
    ParcelRequest find(long id);
    List<ParcelRequest> findAll();
    List<ParcelRequest> findAllByUser(long id);
    List<ParcelRequest> findAllPending();
    boolean delete(long id);
}
