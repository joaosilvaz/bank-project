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
import br.com.fiap.bank_project.model.AccountStatus;

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

    // POST
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

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Account> closeAccount(@PathVariable Long id) {
        Account account = getAccount(id);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log.info("Encerrando conta " + id + " " + account);

        account.setAtiva(AccountStatus.INATIVA);

        return ResponseEntity.ok(account);
    }

    // Get account by ID
    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        log.info("Buscando conta por ID" + id);
        return getAccount(id);
    }

    // Get account by CPF
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

    // Deposit
    @PutMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody Transacao depositRequest) {
        log.info("Realizando depósito: " + depositRequest);

        var contaDestino = getById(depositRequest.destino());

        contaDestino.deposit(depositRequest.valor());

        return ResponseEntity.status(200).body(contaDestino);

    }

    // Withdraw
    @PutMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestBody Transacao withdrawRequest) {
        log.info("Realizando saque: " + withdrawRequest);

        var contaOrigem = getById(withdrawRequest.origem());

        contaOrigem.withdraw(withdrawRequest.valor());

        return ResponseEntity.status(200).body(contaOrigem);

    }

    // Pix
    @PutMapping("/pix")
    public ResponseEntity<?> createPix(@RequestBody Transacao dto) {
        log.info("Fazendo Pix da conta " + dto.origem() + " para a conta " + dto.destino());

        var contaOrigem = getAccount(dto.origem());
        var contaDestino = getAccount(dto.destino());
        try {
            contaOrigem.withdraw(dto.valor());
            contaDestino.deposit(dto.valor());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e);
        }

        return ResponseEntity.status(200).body(contaOrigem);
    }
}
