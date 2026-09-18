package com.nexusdeals.nexus_deals.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String marca;

    private String categoria;

    private String sku;

    private String ean;

    private String imagemUrl;

    public Produto(){
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
    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public String getSku(){
        return sku;
    }
    public void setSku(String sku){
        this.sku = sku;
    }
    public String getEan(){
        return ean;
    }
    public void setEan(String ean){
        this.ean = ean;
    }
    public String getImagemUrl(){
        return imagemUrl;
    }
    public void setImagemUrl(String imagemUrl){
        this.imagemUrl = imagemUrl;
    }
}