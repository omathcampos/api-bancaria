package com.example.apibancaria.service;

import com.example.apibancaria.dto.PessoaFisicaDto;
import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaRepository.findAll();
    }

    public PessoaFisica listarPessoaFisicasPorId(Long id) {
        return pessoaFisicaRepository.findById(id).get();
    }

    public PessoaFisica cadastroPF(PessoaFisicaDto pessoaFisicaDto) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(pessoaFisicaDto.getNome());
        pessoaFisica.setCpf(pessoaFisicaDto.getCpf());
        pessoaFisica.setRg(pessoaFisicaDto.getRg());
        pessoaFisica.setDataNascimento(pessoaFisicaDto.getDataNascimento());
        pessoaFisica.setEndereco(pessoaFisicaDto.getEndereco());
        return pessoaFisicaRepository.save(pessoaFisica);
    }
}
