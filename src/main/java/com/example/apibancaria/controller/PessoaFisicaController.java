package com.example.apibancaria.controller;

import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.service.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-bancaria")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping("/cadastroPF")
    public ResponseEntity salvar(@RequestBody PessoaFisica pessoaFisica) {
        pessoaFisicaService.cadastroPF(pessoaFisica);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa Fisíca cadastrada com sucesso!");
    }

}
