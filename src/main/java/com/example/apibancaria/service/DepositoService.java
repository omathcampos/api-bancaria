package com.example.apibancaria.service;

import com.example.apibancaria.Enum.TipoTransacao;
import com.example.apibancaria.dto.DepositoDto;
import com.example.apibancaria.exception.custom.CustomNotFound;
import com.example.apibancaria.exception.custom.CustomNullPointerException;
import com.example.apibancaria.model.ContaBancaria;
import com.example.apibancaria.model.Transacao;
import com.example.apibancaria.repository.ContaBancariaRepository;
import com.example.apibancaria.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DepositoService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao depositar(DepositoDto dto) {
        if (dto.getContaId() == null) {
            throw new CustomNullPointerException("Informe a conta para depósito");
        }
        if (dto.getValor() == null || dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomNullPointerException("Valor do depósito deve ser positivo");
        }

        ContaBancaria conta = contaBancariaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new CustomNotFound("Conta não encontrada: " + dto.getContaId()));

        conta.setSaldo(conta.getSaldo().add(dto.getValor()));
        contaBancariaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setContaBancaria(conta);
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setValor(dto.getValor());
        transacao.setMomentoTransacao(LocalDateTime.now());
        return transacaoRepository.save(transacao);
    }
}


