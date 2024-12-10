package com.example.apibancaria.service;

import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisica cadastroPF(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }
}
