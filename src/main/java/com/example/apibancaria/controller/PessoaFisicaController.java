package com.example.apibancaria.controller;

import com.example.apibancaria.dto.PessoaFisicaDto;
import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.service.PessoaFisicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-bancaria")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @GetMapping("/listar-pessoas-fisicas")
    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaService.listarPessoasFisicas();
    }

    @GetMapping("/buscar-pessoa-fisica/{id}")
    public PessoaFisica buscarPessoaFisicaPorId(@PathVariable Long id) {
        return pessoaFisicaService.listarPessoaFisicasPorId(id);
    }

    @PostMapping("/cadastroPF")
    public ResponseEntity salvar(@RequestBody @Valid PessoaFisicaDto pessoaFisicaDto) {
        pessoaFisicaService.cadastroPF(pessoaFisicaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa Fisíca cadastrada com sucesso!");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<PessoaFisica> atualizarPessoaFisica(
            @RequestBody PessoaFisicaDto pessoaFisicaDto,
            @PathVariable Long id) {
        PessoaFisica pessoaAtualizada = pessoaFisicaService.alterandoPF(pessoaFisicaDto, id);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/deletarPF/{id}")
    public ResponseEntity deletandoPessoasFisica(@RequestParam Long id) {
        pessoaFisicaService.deletandoPessoaFisica(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
