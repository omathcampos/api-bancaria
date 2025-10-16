package com.example.apibancaria.service;

import com.example.apibancaria.dto.ContaBancariaDto;
import com.example.apibancaria.exception.custom.CustomConflictException;
import com.example.apibancaria.exception.custom.CustomNotFound;
import com.example.apibancaria.exception.custom.CustomNullPointerException;
import com.example.apibancaria.model.ContaBancaria;
import com.example.apibancaria.model.PessoaFisica;
import com.example.apibancaria.model.PessoaJuridica;
import com.example.apibancaria.repository.ContaBancariaRepository;
import com.example.apibancaria.repository.PessoaFisicaRepository;
import com.example.apibancaria.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    private void validarCampoObrigatorio(Object valor, String nomeCampo) {
        if (valor == null || (valor instanceof String && ((String) valor).isEmpty())) {
            throw new CustomNullPointerException("Preencha o campo " + nomeCampo);
        }
    }

    private void validarCadastro(ContaBancariaDto dto) {
        validarCampoObrigatorio(dto.getNumeroConta(), "número da conta");
        validarCampoObrigatorio(dto.getAgencia(), "agência");
        validarCampoObrigatorio(dto.getSaldo(), "saldo");
        validarCampoObrigatorio(dto.getTipoConta(), "tipo da conta");

        if (contaBancariaRepository.existsByNumeroConta(dto.getNumeroConta())) {
            throw new CustomConflictException("Já existe uma conta com esse número");
        }

        if (dto.getPessoaFisicaId() == null && dto.getPessoaJuridicaId() == null) {
            throw new CustomNullPointerException("Informe pessoa física ou jurídica vinculada à conta");
        }
    }

    private ContaBancaria toEntity(ContaBancariaDto dto) {
        ContaBancaria conta = new ContaBancaria();
        conta.setNumeroConta(dto.getNumeroConta());
        conta.setAgencia(dto.getAgencia());
        conta.setSaldo(dto.getSaldo());
        conta.setTipoConta(dto.getTipoConta());

        if (dto.getPessoaFisicaId() != null) {
            PessoaFisica pf = pessoaFisicaRepository.findById(dto.getPessoaFisicaId())
                    .orElseThrow(() -> new CustomNotFound("Pessoa Física não encontrada: " + dto.getPessoaFisicaId()));
            conta.setPessoaFisica(pf);
        }
        if (dto.getPessoaJuridicaId() != null) {
            PessoaJuridica pj = pessoaJuridicaRepository.findById(dto.getPessoaJuridicaId())
                    .orElseThrow(() -> new CustomNotFound("Pessoa Jurídica não encontrada: " + dto.getPessoaJuridicaId()));
            conta.setPessoaJuridica(pj);
        }
        return conta;
    }

    public ContaBancaria criarConta(ContaBancariaDto dto) {
        validarCadastro(dto);
        try {
            return contaBancariaRepository.save(toEntity(dto));
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao salvar conta bancária: " + e.getMessage());
        }
    }

    public List<ContaBancaria> listarContas() {
        return contaBancariaRepository.findAll();
    }

    public ContaBancaria buscarPorId(Long id) {
        return contaBancariaRepository.findById(id)
                .orElseThrow(() -> new CustomNotFound("Conta não encontrada: " + id));
    }

    public List<ContaBancaria> listarPorPessoaFisica(Long pessoaFisicaId) {
        return contaBancariaRepository.findByPessoaFisica_Id(pessoaFisicaId);
    }

    public List<ContaBancaria> listarPorPessoaJuridica(Long pessoaJuridicaId) {
        return contaBancariaRepository.findByPessoaJuridica_Id(pessoaJuridicaId);
    }

    public ContaBancaria atualizar(Long id, ContaBancariaDto dto) {
        ContaBancaria existente = buscarPorId(id);
        if (dto.getAgencia() != null && !dto.getAgencia().isEmpty()) {
            existente.setAgencia(dto.getAgencia());
        }
        if (dto.getTipoConta() != null) {
            existente.setTipoConta(dto.getTipoConta());
        }
        return contaBancariaRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!contaBancariaRepository.existsById(id)) {
            throw new CustomNotFound("Conta não encontrada: " + id);
        }
        contaBancariaRepository.deleteById(id);
    }
}


