package com.example.apibancaria.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PessoaJuridicaDto {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;
    private String endereco;

    public PessoaJuridicaDto() {
    }

    public PessoaJuridicaDto(Long id, String razaoSocial, String cnpj, LocalDate dataFundacao, String endereco) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}