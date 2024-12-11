package com.example.apibancaria.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

public class PessoaFisicaDto {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório.")
    private String rg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "A data de nascimento é obrigatória.")
    private String dataNascimento;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;


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
