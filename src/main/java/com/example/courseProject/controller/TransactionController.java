package com.example.courseProject.controller;

import com.example.courseProject.forms.TransactionForm;
import com.example.courseProject.model.Client;
import com.example.courseProject.model.CreditCard;
import com.example.courseProject.model.Operation;
import com.example.courseProject.model.Transaction;
import com.example.courseProject.service.CardService;
import com.example.courseProject.service.ClientService;
import com.example.courseProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TransactionController {
    private final ClientService clientService;
    private final CardService cardService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(ClientService clientService, CardService cardService, TransactionService transactionService) {
        this.clientService = clientService;
        this.cardService = cardService;
        this.transactionService = transactionService;
    }

    //удалить транзакцию для карты. Решено убрать удаление транзакций из польз. интерфейса
    @PostMapping("/transactions/{cardId}/delete")
    public String deleteClientCardTransactions(@PathVariable("cardId") String cardNum){
        Client client = cardService.getClientByCard(cardNum);
        transactionService.deleteCardTransactions(cardNum);
        return "redirect:/cards/"+client.getId();
    }

/*    @PostMapping("/transactions/{clientId}/delete")
    public String deleteAllClientTransactions(@PathVariable("clientId") Integer clientId, @PathVariable("cardId") String cardNum){
        cardService.deleteClientCard(clientId,cardNum);
        return "redirect:/cards/"+clientId;
    }*/

    //транзакции по определенной карте для админа
    @GetMapping("/transactions/card/{cardNum}")
    public String getCardTransactionPageByAdmin(Model model, @PathVariable("cardNum") String cardNum){
        List <Transaction> transactions = transactionService.findByCreditCard(cardNum);
        CreditCard creditCard = cardService.findByCardNum(cardNum);
        model.addAttribute("transactions", transactions);
        model.addAttribute("card", creditCard);
        return "cardTransactions";
    }

    //транзакции по определенному пользователю для админа
    @GetMapping("/transactions/client/{id}")
    public String getTransactionPageByAdmin(Model model, @PathVariable("id") Integer id){
        List <Transaction> transactions = transactionService.findByClientId(id);
        List<CreditCard> creditCards = cardService.findByClientId(id);
        model.addAttribute("transactions", transactions);
        model.addAttribute("cards", creditCards);
        return "transactions";
    }

    //страница транзакций по карте для клиента
    @GetMapping("/profile/transactions/{cardNum}")
    public String getCardTransactionPageByUser(@AuthenticationPrincipal(expression = "id") Integer clientIdNow, Model model, @PathVariable(name = "cardNum") String cardNum){
        model.addAttribute("client", clientService.getClientById(clientIdNow));
        List <Transaction> transactions = transactionService.findByCreditCard(cardNum);
        CreditCard creditCard = cardService.findByCardNum(cardNum);
        model.addAttribute("transactions", transactions);
        model.addAttribute("card", creditCard);
        return "ClientTemplates/cardTransactions";
    }

    //страница всех транзакций для клиента
    @GetMapping("/profile/transactions")
    public String getTransactionPageByUser(@AuthenticationPrincipal(expression = "id") Integer clientIdNow, Model model){
        model.addAttribute("client", clientService.getClientById(clientIdNow));
        List <Transaction> transactions = transactionService.findByClientId(clientIdNow);
        List<CreditCard> creditCards = cardService.findByClientId(clientIdNow);
        model.addAttribute("transactions", transactions);
        model.addAttribute("cards", creditCards);
        return "ClientTemplates/transactions";
    }

    //страница новой транзакции
    @GetMapping("profile/transactions/new")
    public String getNewTransactionPage(Model model,@AuthenticationPrincipal(expression = "id") Integer clientIdNow){
        Client client = clientService.getClientById(clientIdNow);
        List<CreditCard> creditCards = cardService.findByClientId(clientIdNow);
        List<Operation> operations = transactionService.getOperations();
        model.addAttribute("client", client);
        model.addAttribute("cards", creditCards);
        model.addAttribute("operations", operations);
        return "ClientTemplates/newTransaction";
    }

    //подтвердить транзакцию
    @PostMapping("profile/transactions/new")
    public String getNewTransactionPage(TransactionForm form, @AuthenticationPrincipal(expression = "id") Integer clientIdNow){

        transactionService.makeTransaction(form);
        return "redirect:/homepage";
    }

}
