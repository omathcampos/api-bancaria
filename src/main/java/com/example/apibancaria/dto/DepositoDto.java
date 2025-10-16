package com.example.apibancaria.dto;

import java.math.BigDecimal;

public class DepositoDto {
    private Long contaId;
    private BigDecimal valor;

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}


