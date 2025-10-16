package com.example.apibancaria.repository;

import com.example.apibancaria.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
    boolean existsByNumeroConta(String numeroConta);
    List<ContaBancaria> findByPessoaFisica_Id(Long pessoaFisicaId);
    List<ContaBancaria> findByPessoaJuridica_Id(Long pessoaJuridicaId);
}


