package com.example.courseProject.controller;

import com.example.courseProject.forms.ClientForm;
import com.example.courseProject.model.Client;
import com.example.courseProject.model.Contact;
import com.example.courseProject.model.CreditCard;
import com.example.courseProject.repository.ContactRepository;
import com.example.courseProject.service.CardService;
import com.example.courseProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    private final ClientService clientService;
    private final CardService cardService;
    private final ContactRepository contactRepository;

    @Autowired
    public ClientController(ClientService clientService, CardService cardService, ContactRepository contactRepository) {
        this.clientService = clientService;
        this.cardService = cardService;
        this.contactRepository =contactRepository;
    }

    //возвращает страницу со всеми клиентами. На внешке выводится таблицей
    @GetMapping("/clients")
    public String getAllClientsPageByAdmin(Model model){
        List <Client> clientList = clientService.getClientsByAll();
        clientList.sort(Client::compareTo);
        List <Contact> contactList = new ArrayList<>();
        model.addAttribute("clients", clientList);
        for (Client client: clientList
             ) {
            contactList.add(client.getContact());
            System.out.println(client);
        }
        model.addAttribute("contacts", contactList);
        return "clients";
    }

    //страница редактирования информации о клиента для админа
    @GetMapping("/clients/{id}")
    public String getClientPageByAdmin(Model model, @PathVariable ("id") Integer id){
        Client client = clientService.getClientById(id);
        model.addAttribute("bankClient", client);
        return "clientEdit";
    }

    //обработчик изменения клиента
    @PostMapping("/clients/{id}/update")
    public String updateClientByAdmin(@Valid ClientForm clientForm, @PathVariable ("id") Integer id,  BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("errors", "Форма заполнена неверно");
            return "redirect:/clients/"+id;
        }
        clientService.updateClient(id, clientForm);
        return "redirect:/clients";
    }

    //удаление клиента
    @PostMapping("clients/{id}/delete")
        public String deleteClientByAdmin(@PathVariable ("id") Integer id){
        clientService.deleteClientWithCardsAndTransactions(id);
        return "redirect:/clients";
    }

    //начальная страница авторизованного пользователя
    @GetMapping("/homepage")
    public String getProfilePage(@AuthenticationPrincipal(expression = "id") Integer clientIdNow, Model model) {
        model.addAttribute("user", clientService.getClientById(clientIdNow));
        if(clientService.getClientById(clientIdNow).getRole() == Client.Role.ADMIN) {
            return "redirect:/clients";
        } else {
            List<CreditCard> creditCards = cardService.findByClientId(clientIdNow);
            model.addAttribute("cards", creditCards);
            return "ClientTemplates/clientPage";
        }
    }

    //страница редактирования профиля
    @GetMapping("/profile")
    public String getClientPage(@AuthenticationPrincipal(expression = "id") Integer clientIdNow,Model model){
        Client client = clientService.getClientById(clientIdNow);
        model.addAttribute("bankClient", client);
        return "ClientTemplates/clientEdit";
    }

    //обработчик редактирования профиля
    @PostMapping("/profile/update")
    public String updateClient(@AuthenticationPrincipal(expression = "id") Integer clientIdNow, @Valid ClientForm clientForm,  BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()){
            attributes.addFlashAttribute("errors", "Форма заполнена неверно");
            return "redirect:/profile";
        }
        clientService.updateClient(clientIdNow, clientForm);
        return "redirect:/homepage";
    }

    @GetMapping("/back")
    public String getAdminPage() {
        return "adminPage";
    }
}
