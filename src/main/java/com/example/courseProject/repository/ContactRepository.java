package com.example.courseProject.repository;

import com.example.courseProject.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findById(Integer id);
    Optional<Contact> findByPhone(String phone);
    Optional<Contact> findByEmail(String email);
    Optional<Contact> findByTelegram(String telegram);
    Optional<Contact> findByVk(String vk);
}
