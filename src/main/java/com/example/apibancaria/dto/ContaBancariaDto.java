package com.example.apibancaria.dto;

import com.example.apibancaria.Enum.TipoConta;

import java.math.BigDecimal;

public class ContaBancariaDto {

    private String numeroConta;

    private String agencia;

    private BigDecimal saldo;

    private TipoConta tipoConta;

    private Long pessoaFisicaId;

    private Long pessoaJuridicaId;

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Long getPessoaFisicaId() {
        return pessoaFisicaId;
    }

    public void setPessoaFisicaId(Long pessoaFisicaId) {
        this.pessoaFisicaId = pessoaFisicaId;
    }

    public Long getPessoaJuridicaId() {
        return pessoaJuridicaId;
    }

    public void setPessoaJuridicaId(Long pessoaJuridicaId) {
        this.pessoaJuridicaId = pessoaJuridicaId;
    }
}


