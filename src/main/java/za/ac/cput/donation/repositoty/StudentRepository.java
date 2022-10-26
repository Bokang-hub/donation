/*
Author: Xola Mngeni (218268017) ADP3 Assignment 1.
Date: 07 April 2022*/
package za.ac.cput.donation.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.donation.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailAndPassword(String email, String password);
}
