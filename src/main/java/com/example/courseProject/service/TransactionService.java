package com.example.courseProject.service;

import com.example.courseProject.forms.ClientForm;
import com.example.courseProject.forms.TransactionForm;
import com.example.courseProject.model.*;
import com.example.courseProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private ClientRepository clientRepository;

    private boolean isCurrencyEqual(CreditCard creditCard,CreditCard creditCard1){
        return creditCard.getCurrency().equals(creditCard1.getCurrency());
    }

    //совершить перевод (или платеж) в валюте
    public void makeTransaction(TransactionForm form){
        System.out.println(form);
        CreditCard senderCard = creditCardRepository.findByCardNum(form.getSender()).get();
        CreditCard recipientCard = creditCardRepository.findByCardNum(form.getRecipient()).get();

        //исходное и конечное кол-во денег на платеж
        Double moneyFrom = form.getMoney();
        Double moneyTo = form.getMoney();

        if (senderCard != null && (senderCard.getMoney()-moneyFrom>=0)){
            if (!isCurrencyEqual(senderCard,recipientCard)){
                Currency currencyFrom = senderCard.getCurrency();
                Currency currencyTo = recipientCard.getCurrency();
                // эта сложная формула - перевод денег из одной валюты в другую.
                // Деньги будут на карте получателя в его валюте, от отправителя уйдут же в его валюте.
                moneyTo = moneyFrom * (currencyFrom.getValue()/currencyFrom.getNominal())
                        / currencyTo.getValue() * currencyTo.getNominal();
            }
            Transaction transaction = Transaction.builder()
                    .senderCard(senderCard.getCardNum())
                    .recipientCard(recipientCard.getCardNum())
                    .money(moneyFrom)
                    .transactionDate(new Date())
                    .operation(operationRepository.findByOperationName(form.getOperation()).get())
                    .build();
            transactionRepository.save(transaction);
            System.out.println("Успешная транзакция" + transaction);

            senderCard.setMoney(senderCard.getMoney() - moneyFrom);
            recipientCard.setMoney(recipientCard.getMoney() + moneyTo);

            creditCardRepository.save(senderCard);
            creditCardRepository.save(recipientCard);
        }
        else System.out.println("Неправильно введены данные");
    }

    public List<Transaction> findByDateAfter(Date date){
        return transactionRepository.findByTransactionDateAfter(date);
    }
    public List<Transaction> findByDateBefore(Date date){
        return transactionRepository.findByTransactionDateBefore(date);
    }
    public List<Transaction> findByDate(Date date){
        return transactionRepository.findByTransactionDateOrderByTransactionDateDesc(date);
    }
    public List<Transaction> findByMoneyMore(double money){
        return transactionRepository.findByMoneyGreaterThanEqualOrderByMoneyDesc(money);
    }
    public List<Transaction> findByMoneyLess(double money){
        return transactionRepository.findByMoneyLessThanEqualOrderByMoneyAsc(money);
    }

    public List<Transaction> findByCreditCard(String cardNum){
        return transactionRepository.findBySenderCard(cardNum);
    }

    public List<Transaction> findByClientId(Integer id){
        Client client = clientRepository.findById(id).get();
        List <CreditCard> creditCards = creditCardRepository.findByClientId(id);
        List<Transaction> transactions = new ArrayList<>();
        for (CreditCard card: creditCards) {
            transactions.addAll(card.getTransactionList());
        }
        return transactions;
    }

    public void deleteCardTransactions(String cardNum){
        List <Transaction> transactions = transactionRepository.findBySenderCard(cardNum);
        transactionRepository.deleteAll(transactions);
    }

    public List<Operation> getOperations(){
        return operationRepository.findAll();
    }

}
