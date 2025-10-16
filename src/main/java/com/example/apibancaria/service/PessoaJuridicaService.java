package com.example.apibancaria.service;

import com.example.apibancaria.dto.PessoaJuridicaDto;
import com.example.apibancaria.exception.custom.CustomConflictException;
import com.example.apibancaria.exception.custom.CustomNotFound;
import com.example.apibancaria.exception.custom.CustomNullPointerException;
import com.example.apibancaria.model.PessoaJuridica;
import com.example.apibancaria.repository.PessoaJuridicaRepository;
import com.example.apibancaria.util.CpfCnpjValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaJuridicaService.class);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private void validarCampoObrigatorio(String valor, String nomeCampo) {
        if (valor == null || valor.isEmpty()) {
            throw new CustomNullPointerException("Preencha o campo " + nomeCampo);
        }
    }

    private LocalDate parseData(String dataStr) {
        try {
            return LocalDate.parse(dataStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new CustomNullPointerException("Data inválida. Use o formato dd/MM/yyyy");
        }
    }

    private void validarCadastro(PessoaJuridicaDto dto) {
        validarCampoObrigatorio(dto.getRazaoSocial(), "razão social");
        validarCampoObrigatorio(dto.getCnpj(), "CNPJ");
        validarCampoObrigatorio(dto.getDataFundacao(), "data de fundação");
        validarCampoObrigatorio(dto.getEndereco(), "endereço");

        if (!CpfCnpjValidator.isValidCnpj(dto.getCnpj())) {
            throw new CustomNullPointerException("CNPJ inválido");
        }

        if (pessoaJuridicaRepository.existsByCnpj(dto.getCnpj())) {
            throw new CustomConflictException("Já existe um cadastro com esse CNPJ");
        }
        if (pessoaJuridicaRepository.existsByRazaoSocial(dto.getRazaoSocial())) {
            throw new CustomConflictException("Já existe um cadastro com essa razão social");
        }
    }

    private PessoaJuridica toEntity(PessoaJuridicaDto dto) {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setRazaoSocial(dto.getRazaoSocial());
        pj.setCnpj(dto.getCnpj());
        pj.setDataFundacao(parseData(dto.getDataFundacao()));
        pj.setEndereco(dto.getEndereco());
        return pj;
    }

    public List<PessoaJuridica> listarTodas() {
        return pessoaJuridicaRepository.findAll();
    }

    public PessoaJuridica buscarPorId(Long id) {
        return pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new CustomNotFound("Pessoa Jurídica não encontrada: " + id + " Busque por outro ID"));
    }

    public PessoaJuridica cadastrar(PessoaJuridicaDto dto) {
        validarCadastro(dto);
        try {
            PessoaJuridica pj = toEntity(dto);
            PessoaJuridica salvo = pessoaJuridicaRepository.save(pj);
            LOGGER.info("Pessoa Jurídica cadastrada com sucesso");
            return salvo;
        } catch (DataAccessException e) {
            LOGGER.error("Erro ao salvar pessoa jurídica no banco de dados: {}", e.getMessage());
            throw new RuntimeException("Erro ao salvar pessoa jurídica no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro inesperado ao cadastrar Pessoa Jurídica: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao cadastrar pessoa jurídica: " + e.getMessage());
        }
    }

    public PessoaJuridica atualizar(Long id, PessoaJuridicaDto dto) {
        validarCampoObrigatorio(dto.getRazaoSocial(), "razão social");
        validarCampoObrigatorio(dto.getEndereco(), "endereço");

        PessoaJuridica existente = buscarPorId(id);
        existente.setRazaoSocial(dto.getRazaoSocial());
        existente.setEndereco(dto.getEndereco());
        return pessoaJuridicaRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!pessoaJuridicaRepository.existsById(id)) {
            LOGGER.error("Pessoa jurídica com id: {} não encontrada", id);
            throw new CustomNotFound("Pessoa Jurídica não encontrada com ID: " + id);
        }
        pessoaJuridicaRepository.deleteById(id);
        LOGGER.info("Pessoa jurídica de id {} deletada com sucesso", id);
    }
}


