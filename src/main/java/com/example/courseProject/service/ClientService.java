package com.example.courseProject.service;

import com.example.courseProject.forms.ClientForm;
import com.example.courseProject.model.Client;
import com.example.courseProject.model.Contact;
import com.example.courseProject.model.CreditCard;
import com.example.courseProject.model.Transaction;
import com.example.courseProject.repository.ClientRepository;
import com.example.courseProject.repository.ContactRepository;
import com.example.courseProject.repository.CreditCardRepository;
import com.example.courseProject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Client> getClientsByAll(){
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id){
        return clientRepository.getById(id);
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    //добавление нового клиента. Не используется по причине добавление в классе signupservice при регистрации
    public void addClient(ClientForm clientForm) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = simpleDateFormat.parse(clientForm.getBirthDay());
        java.sql.Date sqlDate = new Date(date.getTime());

        Client client = Client.builder()
                .firstName(clientForm.getFirstName())
                .secondName(clientForm.getSecondName())
                .lastName(clientForm.getLastName())
                .birthDate(sqlDate)
                .build();
        Contact contact = Contact.builder()
                .client(client)
                .email(clientForm.getEmail())
                .phone(clientForm.getPhone())
                .meta(clientForm.getMeta())
                .telegram(clientForm.getTelegram())
                .vk(clientForm.getVk())
                .build();
        clientRepository.save(client);
        contactRepository.save(contact);
    }

    //изменение клиента
    public void updateClient(Integer userId, ClientForm clientForm) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = simpleDateFormat.parse(clientForm.getBirthDay());
        java.sql.Date sqlDate = new Date(date.getTime());

        Contact contact =  clientRepository.findById(userId).get().getContact();
        contact.setEmail(clientForm.getEmail());
        contact.setPhone(clientForm.getPhone());
        contact.setMeta(clientForm.getMeta());
        contact.setTelegram(clientForm.getTelegram());
        contact.setVk(clientForm.getVk());

        Client before = clientRepository.getById(userId);
        Client client = Client.builder()
                .id(userId)
                .firstName(clientForm.getFirstName())
                .lastName(clientForm.getLastName())
                .secondName(clientForm.getSecondName())
                .birthDate(sqlDate)
                .role(before.getRole())
                .userName(before.getUserName())
                .hashPassword(before.getHashPassword())
                .creditCardList(before.getCreditCardList())
                .contact(contact)
                .build();
        clientRepository.save(client);
        contactRepository.save(contact);
    }

    public void deleteClient(Integer userId){
        clientRepository.deleteById(userId);
    }

    public List<CreditCard> getUserCreditCards(Integer userId){
        Client client = clientRepository.getById(userId);
        return client.getCreditCardList();
    }

    //удаление пользователя со всеми картами и транзакциями
    public void deleteClientWithCardsAndTransactions(Integer userId){
        Client client = clientRepository.getById(userId);
        List<CreditCard> creditCards = client.getCreditCardList();
        List<Transaction> transactions = new ArrayList<>();
        for (CreditCard creditCard:creditCards) {
            transactions.addAll(creditCard.getTransactionList());
        }

        for (Transaction transaction: transactions) {
            transactionRepository.delete(transaction);
        }

        for (CreditCard clientCard: creditCards) {
            creditCardRepository.delete(clientCard);
        }

        clientRepository.deleteById(userId);
    }

    public List<Client> findByBirthday(Date date){
        return clientRepository.findByBirthDate(date);
    }

    public List<Client> findByFirstNameAndSecondNameAndLastName(String firstName, String secondName, String lastName){
        return clientRepository.findByFirstNameAndSecondNameAndLastName(firstName, secondName, lastName);
    }
}
