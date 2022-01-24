package com.example.courseProject.repository;

import com.example.courseProject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByFirstName(String firstName);
    List<Client> findBySecondName(String secondName);
    List<Client> findByLastName(String lastName);
    List<Client> findByFirstNameAndSecondNameAndLastName(String firstName, String secondName, String lastName);
    List<Client> findByBirthDate(java.sql.Date birthDate);
    List<Client> findByFirstNameAndSecondNameAndLastNameAndBirthDate(String firstName, String secondName, String lastName, Date birthDate);

    Optional<Client> findByUserName(String userName);
}
