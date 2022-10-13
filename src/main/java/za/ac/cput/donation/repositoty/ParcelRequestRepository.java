package za.ac.cput.donation.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.donation.entity.ParcelRequest;

import java.util.List;

public interface ParcelRequestRepository extends JpaRepository<ParcelRequest, Long> {
    List<ParcelRequest> findAllByIsReceivedFalse();
    List<ParcelRequest> findAllByStudentId(long id);
}
