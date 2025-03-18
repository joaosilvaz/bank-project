package br.com.fiap.bank_project.model;

import java.time.LocalDate;
import java.util.Random;

public class Account {

    // Atributes
    private Long id;
    private Long numero;
    private int agencia;
    private String nomeTitular;
    private String cpf;
    private LocalDate dataAbertura;
    private double saldoInicial;
    private AccountStatus ativa;
    private AccountType tipo;

    // Constructor
    public Account(Long id, Long numero, int agencia, String nomeTitular, String cpf, LocalDate dataAbertura,
            double saldoInicial, AccountStatus ativa, AccountType tipo) {
        this.id = (id == null) ? Math.abs(new Random().nextLong()) : id;
        this.numero = numero;
        this.agencia = agencia;
        setNomeTitular(nomeTitular);
        setCpf(cpf);
        setDataAbertura(dataAbertura);
        setSaldoInicial(saldoInicial);
        this.ativa = AccountStatus.ATIVA;
        setTipo(tipo);
    }

    public Account() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        if (nomeTitular == null || nomeTitular.isEmpty()) {
            throw new IllegalArgumentException("Nome do Titular não pode ser nulo ou vazio");
        } else {
            this.nomeTitular = nomeTitular;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("Cpf não pode ser nulo ou vazio");
        } else {
            this.cpf = cpf;
        }
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        if (dataAbertura == null || dataAbertura.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de abertura não pode ser nula");
        } else {
            this.dataAbertura = dataAbertura;
        }
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo");
        } else {
            this.saldoInicial = saldoInicial;
        }
    }

    public AccountStatus getAtiva() {
        return ativa;
    }

    public void setAtiva(AccountStatus ativa) {
        this.ativa = ativa;
    }

    public AccountType getTipo() {
        return tipo;
    }

    public void setTipo(AccountType tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de conta não pode ser nulo");
        }
        this.tipo = tipo;
    }

    // Withdraw
    public void withdraw(double value) {
        if (value <= 0)
            throw new IllegalArgumentException("Valor do saque deve ser maior que zero");
        if (saldoInicial < value)
            throw new IllegalArgumentException("Saldo insuficiente");
        saldoInicial -= value;
    }

    // Deposit
    public void deposit(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Valor de deposito não pode ser negativo.");
        saldoInicial += value;
    }

}
