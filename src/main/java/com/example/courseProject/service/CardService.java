package com.example.courseProject.service;

import com.example.courseProject.model.Client;
import com.example.courseProject.model.CreditCard;
import com.example.courseProject.repository.ClientRepository;
import com.example.courseProject.repository.ContactRepository;
import com.example.courseProject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<CreditCard> findByClientId(Integer id){
        return creditCardRepository.findByClientId(id);
    }

    public CreditCard findById(Integer id){
        return creditCardRepository.getById(id);
    }

    public void deleteClientCard(Integer clientId, Integer cardId){
        Client client = clientRepository.getById(clientId);
    }

    public CreditCard findByCardNum(String cardNum){
        return creditCardRepository.findByCardNum(cardNum).get();
    }

    public Client getClientByCard(String cardNum){
        return creditCardRepository.findByCardNum(cardNum).get().getClient();
    }
}
