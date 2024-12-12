package com.example.apibancaria.service;

import com.example.apibancaria.dto.PessoaFisicaDto;
import com.example.apibancaria.exception.custom.CustomConflictException;
import com.example.apibancaria.exception.custom.CustomNotFound;
import com.example.apibancaria.exception.custom.CustomNullPointerException;
import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.repository.PessoaFisicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaFisicaService.class);


    private PessoaFisica conversor(PessoaFisicaDto pessoaFisicaDto) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(pessoaFisicaDto.getNome());
        pessoaFisica.setCpf(pessoaFisicaDto.getCpf());
        pessoaFisica.setRg(pessoaFisicaDto.getRg());
        pessoaFisica.setDataNascimento(pessoaFisicaDto.getDataNascimento());
        pessoaFisica.setEndereco(pessoaFisicaDto.getEndereco());
        return pessoaFisica;
    }

    private void validandoValoresCadastro(PessoaFisicaDto pessoaFisicaDto) {
        validarCampoVazio(pessoaFisicaDto.getNome(), "nome");
        validarCampoVazio(pessoaFisicaDto.getCpf(), "cpf");
        validarCampoVazio(pessoaFisicaDto.getRg(), "RG");
        validarCampoVazio(pessoaFisicaDto.getEndereco(), "endereço");
        validarDataNascimento(pessoaFisicaDto.getDataNascimento());

        if (pessoaFisicaRepository.existsByCpf(pessoaFisicaDto.getCpf())) {
            throw new CustomConflictException("Já existe um cadastro com esse CPF");
        }

        if (pessoaFisicaRepository.existsByRg(pessoaFisicaDto.getRg())) {
            throw new CustomConflictException("Já existe um cadastro com esse RG");
        }
    }

    private void validandoAlteracaoValores(PessoaFisicaDto pessoaFisicaDto) {
        validarCampoVazio(pessoaFisicaDto.getNome(), "nome");
        validarCampoVazio(pessoaFisicaDto.getEndereco(), "endereco");
    }

    private void validarCampoVazio(String campo, String nomeCampo) {
        if (campo == null || campo.isEmpty()) {
            throw new CustomNullPointerException("Preencha o campo " + nomeCampo);
        }
    }

    private void validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new CustomNullPointerException("Preencha o campo Data de Nascimento");
        }

        LocalDate hoje = LocalDate.now();
        if (dataNascimento.isAfter(hoje)) {
            throw new CustomConflictException("A Data de Nascimento não pode ser uma data futura");
        }
    }

    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaRepository.findAll();
    }

    public PessoaFisica listarPessoaFisicasPorId(Long id) {
        return pessoaFisicaRepository.findById(id).orElseThrow(() -> new CustomNotFound("Pessoa Fisica não encontrada: " + id + " Busque por outro ID"));
    }

    public PessoaFisica cadastroPF(PessoaFisicaDto pessoaFisicaDto) {
        validandoValoresCadastro(pessoaFisicaDto);
        try {
            PessoaFisica pessoaFisica = conversor(pessoaFisicaDto);
            PessoaFisica pessoaCadastrada = pessoaFisicaRepository.save(pessoaFisica);
            LOGGER.info("Pessoa Física cadastrada com sucesso");
            return pessoaCadastrada;
        } catch (DataAccessException e) {
            LOGGER.error("Erro ao salvar pessoa física no banco de dados: {}", e.getMessage());
            throw new RuntimeException("Erro ao salvar pessoa física no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Erro inesperado ao cadastrar Pessoa Física: {}", e.getMessage());
            throw new RuntimeException("Erro inesperado ao cadastrar pessoa física: " + e.getMessage());
        }
    }

    public PessoaFisica alterandoPF(PessoaFisicaDto pessoaFisicaDto, Long id) {
        validandoAlteracaoValores(pessoaFisicaDto);

        PessoaFisica pessoaCadastrada = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Física não encontrada com ID: " + id));

        pessoaCadastrada.setNome(pessoaFisicaDto.getNome());
        pessoaCadastrada.setEndereco(pessoaFisicaDto.getEndereco());

        return pessoaFisicaRepository.save(pessoaCadastrada);
    }

    public void deletandoPessoaFisica(Long id) {
        if (!pessoaFisicaRepository.existsById(id)) {
            LOGGER.error("Pessoa com id: " + id + " não encontrada, busque por outro.");
            throw new CustomNotFound("Pessoa Física não encontrada com ID: " + id);
        }
        pessoaFisicaRepository.deleteById(id);
        LOGGER.info("Pessoa física de id " + id + " deletada com sucesso");
    }

}