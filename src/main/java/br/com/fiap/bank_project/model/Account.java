package br.com.fiap.bank_project.model;

import java.time.LocalDate;
import java.util.Random;

public class Account {

    // Atributes

    private Long numero;
    private int agencia;
    private String nomeTitular;
    private String cpf;
    private LocalDate dataAbertura;
    private double saldoInicial;
    private boolean ativa;
    private String tipo;

    // Constructor
    public Account(Long numero, int agencia, String nomeTitular, String cpf, LocalDate dataAbertura, double saldoInicial,
            boolean ativa, String tipo) {
        this.numero = (numero == null) ? new Random().nextLong() : numero;
        this.agencia = agencia;
        this.nomeTitular = nomeTitular;
        this.cpf = cpf;
        this.dataAbertura = dataAbertura;
        this.saldoInicial = saldoInicial;
        this.ativa = ativa;
        this.tipo = tipo;
    }


    // Getters and Setters
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
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Validation
    public void validar() {
        if (nomeTitular == null || nomeTitular.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do titular é obrigatório.");
        }
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF do titular é obrigatório.");
        }
        if (dataAbertura == null || dataAbertura.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de abertura não pode ser no futuro.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
    }
}
