package br.com.fiap.bank_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bank_project.model.Account;

@RestController
@RequestMapping("/account")

public class ControllerBank {

    private List<Account> accounts = new ArrayList<>();

    // Get all accounts
    @GetMapping()
    public List<Account> geList() {
        return accounts;
    }

    // Cadastro POST
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Account account) {
        System.out.println("Cadastrando..." + account.getNomeTitular());
        try{
            accounts.add(account);
            return ResponseEntity.status(201).body(account);
        } catch(RuntimeException e) {
            return ResponseEntity.status(400).body(e);
        }
    }

    


}
