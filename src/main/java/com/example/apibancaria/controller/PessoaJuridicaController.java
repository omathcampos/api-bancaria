package com.example.apibancaria.controller;

import com.example.apibancaria.dto.PessoaJuridicaDto;
import com.example.apibancaria.model.PessoaJuridica;
import com.example.apibancaria.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-bancaria-juridica")
public class PessoaJuridicaController {
    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping("/listar-pessoas-juridicas")
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaService.listarPessoasJuridicas();
    }

    @GetMapping("/buscar-pessoa-juridica/{id}")
    public PessoaJuridica buscarPessoaJuridica(@PathVariable Long id) {
        return pessoaJuridicaService.buscarPessoaJuridicaPorId(id);
    }

    @PostMapping("/cadastro-pessoa-juridica")
    public ResponseEntity cadastrarPessoaJuridica(@RequestBody PessoaJuridicaDto pessoaJuridicaDto) {
        pessoaJuridicaService.cadastroPJ(pessoaJuridicaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa Juridica cadastrada com sucesso!");
    }

    @PutMapping("alterando-pessoa-juridica/{id}")
    public ResponseEntity alterandoPessoaJuridica(
            @RequestBody PessoaJuridicaDto pessoaJuridicaDto,
            @PathVariable Long id) {
       PessoaJuridica pessoaJuridicaAtualizada = pessoaJuridicaService.alterandoPJ(pessoaJuridicaDto, id);
        return ResponseEntity.ok(pessoaJuridicaAtualizada);
    }

    @DeleteMapping("/deletando-pessoa-juridica/{id}")
    public ResponseEntity deletandoPessoaJuridica(@PathVariable Long id) {
        pessoaJuridicaService.deletandoPessoaJuridica(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
