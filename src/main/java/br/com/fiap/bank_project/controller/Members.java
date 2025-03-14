package br.com.fiap.bank_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Members {
    

    @GetMapping()
    public String index(){
        return """
                Nome do Projeto: Bank System
                Integrantes:
                João Vitor da Silva - RM554694
                Fernando Aguiar - RM
                """;
    }

}
