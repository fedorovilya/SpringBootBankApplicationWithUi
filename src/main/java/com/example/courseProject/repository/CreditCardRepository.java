package com.example.courseProject.repository;

import com.example.courseProject.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    List <CreditCard> findByClientId(Integer id);
    Optional<CreditCard>findByCardNum(String cardNum);
    //список карточек, на которых денег в валюте больше указанного
    List<CreditCard> findByCurrencyIdAndMoneyGreaterThanEqual(String currencyId, Double money);
}
