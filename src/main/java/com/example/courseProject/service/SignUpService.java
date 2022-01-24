package com.example.courseProject.service;

import com.example.courseProject.forms.SignUpForm;
import com.example.courseProject.model.Client;
import com.example.courseProject.model.Contact;
import com.example.courseProject.repository.ClientRepository;
import com.example.courseProject.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@Component
public class SignUpService{

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final ContactRepository contactRepository;

    public void signUpUser(SignUpForm form) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = simpleDateFormat.parse(form.getBirthDay());
        java.sql.Date sqlDate = new Date(date.getTime());

        Client client = Client.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .secondName(form.getSecondName())
                .birthDate(sqlDate)
                .userName(form.getUserName())
                .role(Client.Role.USER)
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();
        Contact contact = Contact.builder()
                .phone(null)
                .email(form.getEmail())
                .telegram(null)
                .vk(null)
                .meta(null)
                .client(client)
                .build();
        clientRepository.save(client);
        contactRepository.save(contact);

    }
}
