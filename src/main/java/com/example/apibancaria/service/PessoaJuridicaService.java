package com.example.apibancaria.service;

import com.example.apibancaria.dto.PessoaFisicaDto;
import com.example.apibancaria.dto.PessoaJuridicaDto;
import com.example.apibancaria.exception.custom.CustomConflictException;
import com.example.apibancaria.exception.custom.CustomNotFound;
import com.example.apibancaria.exception.custom.CustomNullPointerException;
import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.model.PessoaJuridica;
import com.example.apibancaria.repository.PessoaJuridicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaJuridicaService.class);

    private PessoaJuridica conversor(PessoaJuridicaDto pessoaJuridicaDto) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj(pessoaJuridicaDto.getCnpj());
        pessoaJuridica.setRazaoSocial(pessoaJuridicaDto.getRazaoSocial());
        pessoaJuridica.setEndereco(pessoaJuridicaDto.getEndereco());
        pessoaJuridica.setDataFundacao(pessoaJuridicaDto.getDataFundacao());
        return pessoaJuridica;
    }

    private void validandoValoresCadastro(PessoaJuridicaDto pessoaJuridicaDto) {
        validarCampoVazio(pessoaJuridicaDto.getCnpj(), "cnpj");
        validarCampoVazio(pessoaJuridicaDto.getRazaoSocial(), "razaoSocial");
        validarCampoVazio(pessoaJuridicaDto.getEndereco(), "endereco");
        validarDataFundacao(pessoaJuridicaDto.getDataFundacao());
    }

    private void validarCampoVazio(String campo, String nomeCampo) {
        if (campo == null || campo.isEmpty()) {
            throw new CustomNullPointerException("Preencha o campo " + nomeCampo);
        }
    }

    private void validarDataFundacao(LocalDate dataFundacao) {
        if (dataFundacao == null) {
            throw new CustomNullPointerException("Preencha o campo Data de Fundação");
        }

        LocalDate hoje = LocalDate.now();
        if (dataFundacao.isAfter(hoje)) {
            throw new CustomConflictException("A Data de Fundação não pode ser uma data futura");
        }
    }

    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaRepository.findAll();
    }

    public PessoaJuridica buscarPessoaJuridicaPorId(Long id) {
        return pessoaJuridicaRepository.findById(id).orElseThrow(() -> new CustomNotFound("Pessoa Juridica não encontrada: " + id + " Busque por outro ID"));
    }

    public PessoaJuridica cadastroPJ(PessoaJuridicaDto pessoaJuridicaDto) {
        validandoValoresCadastro(pessoaJuridicaDto);
        try {
            PessoaJuridica pessoaJuridica = conversor(pessoaJuridicaDto);
            PessoaJuridica pessoajuridicaCadastrada = pessoaJuridicaRepository.save(pessoaJuridica);
            LOGGER.info("Pessoa juridica cadastrado com sucesso!");
            return pessoajuridicaCadastrada;
        } catch (DataAccessException e) {
            LOGGER.error("Erro ao salvar pessoa juridica no banco de dados: {}", e.getMessage());
            throw new RuntimeException("Erro ao salvar pessoa juridica no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro inesperado ao cadastrar Pessoa juridica: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao cadastrar pessoa juridica: " + e.getMessage());
        }
    }

    public PessoaJuridica alterandoPJ(PessoaJuridicaDto pessoaJuridicaDto, Long id) {
        validandoValoresCadastro(pessoaJuridicaDto);

        PessoaJuridica pessoaCadastrada = pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Juridica não encontrada com ID: " + id));

        pessoaCadastrada.setCnpj(pessoaJuridicaDto.getCnpj());
        pessoaCadastrada.setRazaoSocial(pessoaJuridicaDto.getRazaoSocial());
        pessoaCadastrada.setDataFundacao(pessoaJuridicaDto.getDataFundacao());
        pessoaCadastrada.setEndereco(pessoaJuridicaDto.getEndereco());

        return pessoaJuridicaRepository.save(pessoaCadastrada);
    }

    public void deletandoPessoaJuridica(Long id) {
        if(!pessoaJuridicaRepository.existsById(id)) {
            LOGGER.error("Pessoa juridica com id: " + " não encontrada");
            throw new CustomNotFound("Pessoa juridica com id: " + " não encontrada");
        }
        pessoaJuridicaRepository.deleteById(id);
        LOGGER.info("Pessoa física de id " + id + " deletada com sucesso");
    }

}
