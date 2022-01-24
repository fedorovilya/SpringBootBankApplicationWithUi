package com.example.courseProject.repository;

import com.example.courseProject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByTransactionDateOrderByTransactionDateDesc(Date date);
    List<Transaction> findByTransactionDateGreaterThanEqualOrderByTransactionDateDesc(Date date);
    List<Transaction> findByTransactionDateLessThanEqualOrderByTransactionDateDesc(Date date);

    //наверное то же самое, что и выше, сделал для проверки
    List<Transaction> findByTransactionDateAfter(Date date);
    List<Transaction> findByTransactionDateBefore(Date date);

    List<Transaction> findBySenderCard(String cardNum);
    List<Transaction> findByRecipientCard(String cardNum);
    List<Transaction> findBySenderCardAndRecipientCard(String senderCard, String recipientCard);

    List<Transaction> findByMoneyGreaterThanEqualOrderByMoneyDesc(Double money);
    List<Transaction> findByMoneyLessThanEqualOrderByMoneyAsc(Double money);

    //операции по стоимости и номеру карты. Например отловить сомнительные операции
    List<Transaction> findByMoneyGreaterThanEqualAndSenderCardOrderByMoneyDesc(Double money, String cardNum);
    List<Transaction> findByMoneyLessThanEqualAndSenderCardOrderByMoneyAsc(Double money, String cardNum);
}
