package za.ac.cput.donation.service;

//
//* author: Lukhona Tetyana
 //       * student number:218119321
  //      * ADP3 Capstone Project
   //     * Group 6
//
import za.ac.cput.donation.entity.Parcel;

import java.util.List;

public interface IParcelService {
    Parcel save(Parcel parcel);
    Parcel find(long id);
    List<Parcel> findAll();
    List<Parcel> findAllAvailable();
    Parcel update(Parcel parcel);
}
