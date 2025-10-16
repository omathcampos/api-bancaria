package com.example.apibancaria.controller;

import com.example.apibancaria.dto.PessoaJuridicaDto;
import com.example.apibancaria.model.PessoaJuridica;
import com.example.apibancaria.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-bancaria")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping("/listar-pessoas-juridicas")
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaService.listarTodas();
    }

    @GetMapping("/buscar-pessoa-juridica/{id}")
    public PessoaJuridica buscarPessoaJuridicaPorId(@PathVariable Long id) {
        return pessoaJuridicaService.buscarPorId(id);
    }

    @PostMapping("/cadastroPJ")
    public ResponseEntity salvar(@RequestBody @Valid PessoaJuridicaDto pessoaJuridicaDto) {
        pessoaJuridicaService.cadastrar(pessoaJuridicaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa Jurídica cadastrada com sucesso!");
    }

    @PatchMapping("/atualizarPJ/{id}")
    public ResponseEntity<PessoaJuridica> atualizarPessoaJuridica(
            @RequestBody PessoaJuridicaDto pessoaJuridicaDto,
            @PathVariable Long id) {
        PessoaJuridica pessoaAtualizada = pessoaJuridicaService.atualizar(id, pessoaJuridicaDto);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/deletarPJ/{id}")
    public ResponseEntity deletandoPessoaJuridica(@RequestParam Long id) {
        pessoaJuridicaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


