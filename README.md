# 🌟 API Bancária - Sistema de Gerenciamento de Contas e Transações

Esta é uma API REST desenvolvida com Spring Boot 🛠️ para fornecer serviços bancários, como criação de contas, transferências, depósitos e saques, tanto para pessoas físicas (👤) quanto jurídicas (🏢).

## 🚀 Funcionalidades
- **Cadastro de Contas:** Criação de contas para pessoas físicas (CPF) e jurídicas (CNPJ) 📋.
- **Gerenciamento de Transações:** Realize depósitos, saques e transferências entre contas 💸.
- **Consulta de Saldo:** Verifique o saldo em tempo real da conta 💰.
- **Histórico de Transações:** Acompanhe todas as transações realizadas por uma conta 📈.
- **Integração com APIs Externas:** Simule pagamentos e transferências usando RestTemplate para comunicação com outras instituições financeiras 🌐.
- **Segurança:** Proteção das operações com autenticação e autorização utilizando Spring Security 🔐.
- **Validações:** Garantia de dados consistentes, como CPF/CNPJ válidos e saldo suficiente ✅.
- **Tratamento de Exceções:** Retorno de respostas claras com status HTTP para erros como dados inválidos, saldo insuficiente e acessos não autorizados 🚫.

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL (ou outro banco de dados relacional)
- Spring Security
- RestTemplate para integrações com APIs externas
- Lombok para redução de boilerplate
- JUnit e Mockito para testes unitários e de integração

## 📋 Pré-requisitos
- **Java 17+** instalado ☕
- **MySQL 8.0+** ou outro banco de dados configurado 🗄️
- **Maven** instalado para gerenciar dependências 📦
