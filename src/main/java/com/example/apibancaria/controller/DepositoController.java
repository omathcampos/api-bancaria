package com.example.apibancaria.controller;

import com.example.apibancaria.dto.DepositoDto;
import com.example.apibancaria.model.Transacao;
import com.example.apibancaria.service.DepositoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-bancaria")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @PostMapping("/deposito")
    public ResponseEntity efetuarDeposito(@RequestBody @Valid DepositoDto dto) {
        Transacao transacao = depositoService.depositar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }
}


