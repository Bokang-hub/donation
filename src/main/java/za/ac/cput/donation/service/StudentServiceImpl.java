package za.ac.cput.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.entity.Student;
import za.ac.cput.donation.repositoty.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository repository;

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public Student login(String email, String password) {
        return repository.findByEmailAndPassword(email, password).orElse(new Student());
    }

    @Override
    public Student find(long id) {
        return repository.findById(id).orElse(new Student());
    }

    @Override
    public boolean delete(long id) {
        Student delete = this.find(id);
        if(delete != null){
            repository.delete(delete);
            return true;
        }
        return false;
    }

    @Override
    public Student update(Student student) {
        return repository.save(student);
    }
}

