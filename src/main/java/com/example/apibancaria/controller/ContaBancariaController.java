package com.example.apibancaria.controller;

import com.example.apibancaria.dto.ContaBancariaDto;
import com.example.apibancaria.model.ContaBancaria;
import com.example.apibancaria.service.ContaBancariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-bancaria")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping("/criar-conta")
    public ResponseEntity criar(@RequestBody @Valid ContaBancariaDto dto) {
        ContaBancaria conta = contaBancariaService.criarConta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @GetMapping("/listar-contas")
    public List<ContaBancaria> listar() {
        return contaBancariaService.listarContas();
    }

    @GetMapping("/buscar-conta/{id}")
    public ContaBancaria buscarPorId(@PathVariable Long id) {
        return contaBancariaService.buscarPorId(id);
    }

    @GetMapping("/contas-por-pessoa-fisica/{pessoaId}")
    public List<ContaBancaria> listarPorPessoaFisica(@PathVariable Long pessoaId) {
        return contaBancariaService.listarPorPessoaFisica(pessoaId);
    }

    @GetMapping("/contas-por-pessoa-juridica/{pessoaId}")
    public List<ContaBancaria> listarPorPessoaJuridica(@PathVariable Long pessoaId) {
        return contaBancariaService.listarPorPessoaJuridica(pessoaId);
    }

    @PatchMapping("/atualizar-conta/{id}")
    public ContaBancaria atualizar(@PathVariable Long id, @RequestBody ContaBancariaDto dto) {
        return contaBancariaService.atualizar(id, dto);
    }

    @DeleteMapping("/deletar-conta/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        contaBancariaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


