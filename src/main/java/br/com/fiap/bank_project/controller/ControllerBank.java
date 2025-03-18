package br.com.fiap.bank_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank_project.dto.Transacao;
import br.com.fiap.bank_project.model.Account;
import br.com.fiap.bank_project.model.StatusConta;

@RestController
@RequestMapping("/account")
public class ControllerBank {
    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Account> accounts = new ArrayList<>();

    // Get all accounts
    @GetMapping()
    public List<Account> getList() {
        return accounts;
    }

    // Cadastro POST
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Account account) {
        System.out.println("Cadastrando..." + account.getNomeTitular());
        try {
            accounts.add(account);
            return ResponseEntity.status(201).body(account);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> closeAccount(@PathVariable Long id) {
        Account account = getAccount(id); // Supondo que esse método já esteja implementado

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log.info("Encerrando conta " + id + " " + account);

        account.setStatusConta(StatusConta.INATIVA); // Define como INATIVA

        return ResponseEntity.ok(account);
    }

    // Buscar conta por ID
    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        log.info("Buscando conta por ID" + id);
        return getAccount(id);
    }

    // Buscar conta por CPF
    @GetMapping("/cpf/{cpf}")
    public Account getByCpf(@PathVariable String cpf) {
        log.info("Buscando conta por CPF " + cpf);
        return getAccountByCpf(cpf);
    }

    private Account getAccount(Long id) {
        return accounts
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Account getAccountByCpf(String cpf) {
        return accounts
                .stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Endpoint para realizar depósito
    /**
     * @param depositRequest
     * @return
     */
    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody Transacao depositRequest) {
        log.info("Realizando depósito: " + depositRequest);
        
        if (depositRequest.value() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor do depósito deve ser maior que zero");
        }
        
        var contaDestino = getById(depositRequest.origen());
        
        if (.getStatusConta() == StatusConta.INATIVA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível depositar em uma conta inativa");
        }
        
        // Atualiza o saldo da conta
        double newBalance = account.getSaldo() + depositRequest.getValue();
        account.setSaldo(newBalance);
        
        log.info("Depósito realizado com sucesso. Novo saldo: " + newBalance);
        
        return ResponseEntity.ok(account);
    }
}
