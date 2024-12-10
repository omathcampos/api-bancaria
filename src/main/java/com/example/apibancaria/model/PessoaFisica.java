package com.example.apibancaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pessoaFisica")
public class PessoaFisica {
    @OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContaBancaria> contas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "rg", nullable = false, unique = true)
    private String rg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String nome, String cpf, String rg, String dataNascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

