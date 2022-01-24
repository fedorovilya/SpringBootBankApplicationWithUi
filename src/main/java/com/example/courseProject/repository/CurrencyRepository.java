package com.example.courseProject.repository;

import com.example.courseProject.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    Optional<Currency> findByCharCode(String charcode);
    Optional<Currency> findByName(String name);
    Optional<Currency> findByNumCode(Integer numcode);
}
