package com.example.apibancaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoaJuridica")
public class PessoaJuridica {
    @OneToMany(mappedBy = "pessoaJuridica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContaBancaria> contas;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "razaoSocial", nullable = false, unique = true)
    private String razaoSocial;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataFundacao", nullable = false)
    private LocalDate dataFundacao;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String razaoSocial, String cnpj, LocalDate dataFundacao, String endereco) {
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
