# Nexus Deals

Sistema de comparação de precos com historico, alertas e calculo de score de compra entre multiplas lojas.

## Sobre o projeto

O Nexus Deals tem como objetivo ir alem de um simples comparador de precos. Alem de mostrar qual loja tem o menor preco no momento, o sistema acompanha o historico de precos ao longo do tempo, calcula um score de compra combinando preco, frete e reputacao da loja, e permite configurar alertas personalizados por produto.

## Status do projeto

Em desenvolvimento. Etapa atual: modelagem de dados e persistencia.

Concluido:
- Modelagem das entidades centrais (Produto, Loja, Oferta, HistoricoPreco)
- Persistencia com Spring Data JPA e H2

Em andamento ou planejado:
- API REST para cadastro e consulta de produtos e lojas
- Coleta de dados de precos
- Calculo de historico de precos
- Calculo de score de compra
- Sistema de alertas
- Comparacao entre produtos
- Interface de usuario

## Tecnologias utilizadas

Java 21
Spring Boot (Web, Data JPA, Validation)
H2 Database (ambiente de desenvolvimento)
Maven

## Modelagem de dados

O sistema e estruturado em torno de quatro entidades principais.

Produto: item monitorado, com nome, marca, categoria, SKU e EAN.
Loja: fonte da oferta, com nome, link e reputacao.
Oferta: combinacao especifica de um Produto em uma Loja, contendo preco, frete e disponibilidade.
HistoricoPreco: registro do preco de uma Oferta em um determinado momento.

Uma Oferta pertence a um Produto e a uma Loja. Um HistoricoPreco pertence a uma Oferta.

## Sobre a coleta de dados

Este projeto tem carater de estudo de arquitetura e engenharia de dados para portfolio. A coleta de precos em um ambiente de producao dependeria de parcerias ou APIs oficiais de afiliados de cada loja, respeitando os termos de uso de cada plataforma.

## Como executar o projeto

git clone https://github.com/davi-valadares-dias/nexus-deals.git
cd nexus-deals
./mvnw spring-boot:run

A aplicacao fica disponivel em http://localhost:8080. O console do banco H2 pode ser acessado em http://localhost:8080/h2-console.