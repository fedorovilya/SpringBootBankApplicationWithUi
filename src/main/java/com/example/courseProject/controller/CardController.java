package com.example.courseProject.controller;

import com.example.courseProject.model.Client;
import com.example.courseProject.model.CreditCard;
import com.example.courseProject.service.CardService;
import com.example.courseProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CardController {
    private final ClientService clientService;
    private final CardService cardService;

    @Autowired
    public CardController(ClientService clientService, CardService cardService) {
        this.clientService = clientService;
        this.cardService = cardService;
    }

    //список карт пользователя для админа
    @GetMapping("/cards/{id}")
    public String getClientCardsByAdmin(Model model, @PathVariable("id") Integer id){
        Client client = clientService.getClientById(id);
        List<CreditCard> cardList = cardService.findByClientId(id);
        model.addAttribute("bankClient", client);
        model.addAttribute("clientCards", cardList);
        return "cards";
    }

}
