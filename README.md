Nexus Deals

Sistema de comparação de preços com histórico, alertas e cálculo de score de compra entre múltiplas lojas.

Sobre o projeto

O Nexus Deals tem como objetivo ir além de um simples comparador de preços. Além de mostrar qual loja tem o menor preço no momento, o sistema acompanha o histórico de preços ao longo do tempo, calcula um score de compra combinando preço, frete e reputação da loja, e permite que cada usuário configure alertas personalizados.

Status do projeto

Em desenvolvimento. O backend está funcional e a interface visual ainda não foi iniciada.

Concluído:
Modelagem das entidades centrais (Produto, Loja, Oferta, HistoricoPreco, Alerta e Usuario)
Persistência com Spring Data JPA e H2
API REST completa (CRUD) para Loja, Produto, Oferta e HistoricoPreco
Estatísticas de histórico (menor, maior e média) e comparação do preço atual com a média
Score de compra de 0 a 100
Alertas de preço com verificação automática a cada minuto
Comparação entre produtos
Indicador de confiabilidade e frescor dos dados
Autenticação com token JWT, senhas com BCrypt e rotas protegidas
Alertas vinculados a cada usuário

Planejado:
Favoritos por usuário
Papéis de acesso (administrador e usuário comum)
Coleta automática de preços
Interface de usuário

Tecnologias utilizadas

Java 21
Spring Boot (Web, Data JPA, Validation, Security)
JWT (JJWT) e BCrypt
H2 Database (ambiente de desenvolvimento)
Maven
Postman (testes manuais dos endpoints)

Modelagem de dados

O sistema é estruturado em torno de seis entidades.

Produto: item monitorado, com nome, marca, categoria, SKU e EAN.
Loja: fonte da oferta, com nome, link e reputação.
Oferta: combinação de um Produto em uma Loja, com preço, frete e disponibilidade.
HistoricoPreco: registro do preço de uma Oferta em um determinado momento.
Usuario: pessoa cadastrada, com e-mail único e senha armazenada em hash.
Alerta: condição de preço-alvo criada por um Usuario para uma Oferta.

Score de compra

O score vai de 0 a 100 e combina três fatores, com os pesos abaixo.

Preço em relação ao histórico: 50 por cento. Quanto mais abaixo da média histórica, maior a nota. Sem histórico, a nota é neutra (70).
Reputação da loja: 30 por cento. A nota da loja (0 a 5) é convertida para a escala de 0 a 100.
Frete: 20 por cento. Frete grátis vale 100 e a nota cai conforme o frete pesa no preço do produto.

A resposta traz o score total, uma mensagem (por exemplo, "Excelente oportunidade") e o detalhamento de cada fator.

Segurança

Senhas são armazenadas com hash BCrypt e nunca aparecem nas respostas da API.
A autenticação usa token JWT com validade de 24 horas.
Todas as rotas exigem token, exceto /api/auth/** e /h2-console/**.
Cada usuário só consulta e desativa os próprios alertas.

API REST

Para acessar as rotas protegidas, faça o cadastro e o login e envie o token no cabeçalho Authorization, no formato Bearer seguido do token.

Autenticação
POST /api/auth/cadastro
POST /api/auth/login

Lojas
GET /api/lojas
GET /api/lojas/{id}
POST /api/lojas
PUT /api/lojas/{id}
DELETE /api/lojas/{id}

Produtos
GET /api/produtos
GET /api/produtos/{id}
POST /api/produtos
PUT /api/produtos/{id}
DELETE /api/produtos/{id}

Ofertas
GET /api/ofertas
GET /api/ofertas/{id}
GET /api/ofertas/produto/{produtoId}
GET /api/ofertas/{id}/confiabilidade
POST /api/ofertas
PUT /api/ofertas/{id}
DELETE /api/ofertas/{id}

Histórico de preços
GET /api/historico-precos/oferta/{ofertaId}
GET /api/historico-precos/oferta/{ofertaId}/estatisticas
GET /api/historico-precos/oferta/{ofertaId}/comparar?precoAtual=valor
POST /api/historico-precos

Score de compra
GET /api/score-compra/oferta/{ofertaId}

Comparação entre produtos
GET /api/comparacao?produtoIds=1,2

Alertas (do usuário autenticado)
GET /api/alertas
POST /api/alertas
PUT /api/alertas/{id}/desativar
POST /api/alertas/verificar

Sobre a coleta de dados

Este projeto tem caráter de estudo de arquitetura e engenharia de dados para portfólio. A coleta de preços em um ambiente de produção dependeria de parcerias ou APIs oficiais de afiliados de cada loja, respeitando os termos de uso de cada plataforma. Por enquanto, os preços são inseridos manualmente pela API.

Como executar o projeto

git clone https://github.com/davi-valadares-dias/nexus-deals.git
cd nexus-deals
./mvnw spring-boot:run

A aplicação fica disponível em http://localhost:8080. O console do banco H2 pode ser acessado em http://localhost:8080/h2-console.

Notas de desenvolvimento

O banco H2 roda em memória, então os dados são perdidos a cada reinício da aplicação.
A chave secreta do JWT está no application.properties apenas para desenvolvimento. Em produção, ela deve vir de uma variável de ambiente.