# 🚀 Bank System - Projeto Java + Spring Boot 🚀

Este projeto simula um sistema bancário, desenvolvido como uma API REST utilizando **Java** e **Spring Boot**. O sistema oferece funcionalidades básicas de um banco, como criação de contas, consulta de saldo, depósitos, saques e transferências via PIX.

## 📌 Funcionalidades

- **Criação de conta bancária**: Permite criar contas de diferentes tipos (CORRENTE, POUPANÇA, SALÁRIO).
- **Consulta de saldo**: O usuário pode consultar o saldo das suas contas.
- **Depósitos e Saques**: Operações para adicionar ou retirar valores das contas.
- **Transferências via PIX**: Transferências entre contas utilizando o sistema de pagamentos PIX.
- **Encerramento de conta**: Funcionalidade para encerrar uma conta bancária.

## 🔧 Tecnologias utilizadas

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database (para banco de dados em memória)**

## 🛠 Estrutura do Projeto

### Enums:
- **Tipo de Conta**: 
  - CORRENTE
  - POUPANÇA
  - SALÁRIO

- **Status da Conta**:
  - ATIVA
  - INATIVA

### Record:
- **Transacao**: Um record que encapsula os dados de uma operação (conta de origem, conta de destino e valor da transação). Isso garante que as transações sejam imutáveis e o código seja mais enxuto.

### Endpoints da API:
- **POST /contas**: Criação de uma nova conta bancária.
- **GET /contas/{id}**: Consulta de uma conta bancária por ID ou CPF.
- **PUT /contas/{id}/encerrar**: Encerramento de conta.
- **POST /contas/{id}/deposito**: Realização de um depósito.
- **POST /contas/{id}/saque**: Realização de um saque.
- **POST /contas/{id}/transferencia**: Realização de uma transferência via PIX.

## ⚙ Como executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/bank-system.git
