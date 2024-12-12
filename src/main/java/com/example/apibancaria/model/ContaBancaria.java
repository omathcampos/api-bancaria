package com.example.apibancaria.model;

import com.example.apibancaria.Enum.TipoConta;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "conta_bancaria")
public class ContaBancaria {
    @OneToMany(mappedBy = "contaBancaria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes;

    @ManyToOne
    @JoinColumn(name = "pessoa_fisica_id")
    private PessoaFisica pessoaFisica;

    @ManyToOne
    @JoinColumn(name = "pessoa_juridica_id")
    private PessoaJuridica pessoaJuridica;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "numero_conta", nullable = false, unique = true)
    private String numeroConta;

    @Column(name = "agencia", nullable = false)
    private String agencia;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo; // Use BigDecimal para maior precisão em valores monetários

    @Enumerated(EnumType.STRING) // Mapeia a enum como string no banco de dados
    @Column(name = "tipo_conta", nullable = false)
    private TipoConta tipoConta;

    public ContaBancaria() {
    }

    public ContaBancaria(Long id, String numeroConta, String agencia, BigDecimal saldo, TipoConta tipoConta) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
