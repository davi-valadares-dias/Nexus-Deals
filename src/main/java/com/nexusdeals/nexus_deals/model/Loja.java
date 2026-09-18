package com.nexusdeals.nexus_deals.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Audited;

@Entity
@Table(name = "Lojas")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String linkBase;

    private Double reputacao;

    public Loja(){
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getLinkBase(){
        return linkBase;
    }
    public void setLinkBase(String linkBase){
        this.linkBase = linkBase;
    }
    public Double getReputacao(){
        return reputacao;
    }
    public void setReputacao(Double reputacao){
        this.reputacao = reputacao;
    }
}
