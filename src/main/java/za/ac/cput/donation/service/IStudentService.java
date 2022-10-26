/*
Author: Xola Mngeni (218268017) ADP3 Assignment 1.
Date: 07 April 2022*/
package za.ac.cput.donation.service;

import za.ac.cput.donation.entity.Student;

public interface IStudentService {
    Student save(Student student);
    Student find(long id);
    boolean delete(long id);
    Student update(Student student);

    Student login(String email, String password);
}
