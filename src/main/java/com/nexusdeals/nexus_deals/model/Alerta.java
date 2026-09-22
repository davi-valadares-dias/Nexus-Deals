package com.nexusdeals.nexus_deals.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;

    private BigDecimal precoAlvo;

    private Boolean ativo;

    private Boolean disparado;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataDisparo;

    public Alerta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public BigDecimal getPrecoAlvo() {
        return precoAlvo;
    }

    public void setPrecoAlvo(BigDecimal precoAlvo) {
        this.precoAlvo = precoAlvo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getDisparado() {
        return disparado;
    }

    public void setDisparado(Boolean disparado) {
        this.disparado = disparado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataDisparo() {
        return dataDisparo;
    }

    public void setDataDisparo(LocalDateTime dataDisparo) {
        this.dataDisparo = dataDisparo;
    }
}