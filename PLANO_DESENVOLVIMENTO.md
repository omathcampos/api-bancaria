
bom no código, onde vc# 📋 PLANO DE DESENVOLVIMENTO - API BANCÁRIA

## 🎯 Visão Geral
Este documento contém o plano detalhado de microtarefas para completar o desenvolvimento da API Bancária, organizadas por fases e prioridades.

## 📊 Status Atual do Projeto
- ✅ **Estrutura base** - Spring Boot configurado
- ✅ **PessoaFisica** - CRUD completo implementado
- ✅ **Modelos** - PessoaJuridica, ContaBancaria, Transacao criados
- ✅ **Tratamento de exceções** - GlobalExceptionHandler básico
- ❌ **Operações bancárias** - Não implementadas
- ❌ **Segurança** - Spring Security desabilitado
- ❌ **Integrações** - APIs externas não implementadas

---

## 🚀 FASE 1: COMPLETAR ESTRUTURA BÁSICA
*Prioridade: ALTA | Estimativa: 8-10 horas*

### 1.1 Implementar CRUD completo para PessoaJuridica
**Status:** 🔴 Pendente
- [ ] Criar `PessoaJuridicaController.java`
- [ ] Criar `PessoaJuridicaService.java`
- [ ] Criar `PessoaJuridicaRepository.java`
- [ ] Criar `PessoaJuridicaDto.java`
- [ ] Implementar endpoints:
  - `POST /api-bancaria/cadastroPJ` - Cadastrar pessoa jurídica
  - `GET /api-bancaria/listar-pessoas-juridicas` - Listar todas
  - `GET /api-bancaria/buscar-pessoa-juridica/{id}` - Buscar por ID
  - `PATCH /api-bancaria/atualizarPJ/{id}` - Atualizar dados
  - `DELETE /api-bancaria/deletarPJ/{id}` - Remover pessoa jurídica

### 1.2 Implementar CRUD completo para ContaBancaria
**Status:** 🔴 Pendente
- [ ] Criar `ContaBancariaController.java`
- [ ] Criar `ContaBancariaService.java`
- [ ] Criar `ContaBancariaRepository.java`
- [ ] Criar `ContaBancariaDto.java`
- [ ] Implementar endpoints:
  - `POST /api-bancaria/criar-conta` - Criar nova conta
  - `GET /api-bancaria/listar-contas` - Listar todas as contas
  - `GET /api-bancaria/buscar-conta/{id}` - Buscar conta por ID
  - `GET /api-bancaria/contas-por-pessoa/{pessoaId}` - Contas de uma pessoa
  - `PATCH /api-bancaria/atualizar-conta/{id}` - Atualizar dados da conta
  - `DELETE /api-bancaria/deletar-conta/{id}` - Encerrar conta

### 1.3 Implementar validação de CPF e CNPJ
**Status:** 🔴 Pendente
- [ ] Adicionar dependência de validação de CPF/CNPJ no `pom.xml`
- [ ] Criar anotações customizadas `@CPF` e `@CNPJ`
- [ ] Implementar validadores nos DTOs
- [ ] Adicionar validações nos Services
- [ ] Testar validações com dados inválidos

---

## 💰 FASE 2: OPERAÇÕES BANCÁRIAS
*Prioridade: ALTA | Estimativa: 12-15 horas*

### 2.1 Implementar operação de DEPÓSITO
**Status:** 🔴 Pendente
- [ ] Criar `DepositoController.java`
- [ ] Criar `DepositoService.java`
- [ ] Criar `DepositoDto.java`
- [ ] Implementar endpoint: `POST /api-bancaria/deposito`
- [ ] Validações:
  - [ ] Conta existe e está ativa
  - [ ] Valor positivo
  - [ ] Valor dentro dos limites
- [ ] Atualizar saldo da conta automaticamente
- [ ] Registrar transação no histórico

### 2.2 Implementar operação de SAQUE
**Status:** 🔴 Pendente
- [ ] Criar `SaqueController.java`
- [ ] Criar `SaqueService.java`
- [ ] Criar `SaqueDto.java`
- [ ] Implementar endpoint: `POST /api-bancaria/saque`
- [ ] Validações:
  - [ ] Conta existe e está ativa
  - [ ] Saldo suficiente
  - [ ] Valor positivo
  - [ ] Limite diário de saque
- [ ] Atualizar saldo da conta automaticamente
- [ ] Registrar transação no histórico

### 2.3 Implementar operação de TRANSFERÊNCIA
**Status:** 🔴 Pendente
- [ ] Criar `TransferenciaController.java`
- [ ] Criar `TransferenciaService.java`
- [ ] Criar `TransferenciaDto.java`
- [ ] Implementar endpoint: `POST /api-bancaria/transferencia`
- [ ] Validações:
  - [ ] Conta origem existe e tem saldo suficiente
  - [ ] Conta destino existe e está ativa
  - [ ] Contas diferentes (não pode transferir para si mesmo)
  - [ ] Valor positivo
- [ ] Atualizar saldos das duas contas
- [ ] Registrar duas transações (débito e crédito)

### 2.4 Implementar operação de PIX
**Status:** 🔴 Pendente
- [ ] Criar `PixController.java`
- [ ] Criar `PixService.java`
- [ ] Criar `PixDto.java`
- [ ] Implementar endpoint: `POST /api-bancaria/pix`
- [ ] Validações:
  - [ ] Chave PIX válida (CPF, CNPJ, email, telefone)
  - [ ] Conta origem existe e tem saldo suficiente
  - [ ] Limite PIX diário
  - [ ] Horário de funcionamento PIX
- [ ] Atualizar saldos das contas
- [ ] Registrar transações PIX

---

## 📊 FASE 3: CONSULTAS E RELATÓRIOS
*Prioridade: MÉDIA | Estimativa: 6-8 horas*

### 3.1 Implementar endpoint para consulta de saldo
**Status:** 🔴 Pendente
- [ ] Criar endpoint: `GET /api-bancaria/saldo/{contaId}`
- [ ] Validações:
  - [ ] Conta existe
  - [ ] Conta está ativa
- [ ] Retornar saldo atual formatado
- [ ] Adicionar informações da conta (número, agência, tipo)

### 3.2 Implementar endpoint para histórico de transações
**Status:** 🔴 Pendente
- [ ] Criar `TransacaoController.java`
- [ ] Criar `TransacaoService.java`
- [ ] Implementar endpoint: `GET /api-bancaria/historico/{contaId}`
- [ ] Filtros opcionais:
  - [ ] Por período (data início/fim)
  - [ ] Por tipo de transação
  - [ ] Por valor mínimo/máximo
- [ ] Paginação de resultados
- [ ] Ordenação por data (mais recente primeiro)

### 3.3 Implementar CRUD para Transacao
**Status:** 🔴 Pendente
- [ ] Criar `TransacaoRepository.java`
- [ ] Implementar endpoints adicionais:
  - `GET /api-bancaria/transacao/{id}` - Buscar transação específica
  - `GET /api-bancaria/transacoes-por-tipo/{tipo}` - Filtrar por tipo
- [ ] Métodos de consulta no Repository:
  - [ ] Buscar por conta e período
  - [ ] Buscar por tipo de transação
  - [ ] Calcular totais por período

---

## 🔐 FASE 4: SEGURANÇA E INTEGRAÇÃO
*Prioridade: MÉDIA | Estimativa: 8-10 horas*

### 4.1 Configurar Spring Security
**Status:** 🔴 Pendente
- [ ] Habilitar Spring Security no `pom.xml`
- [ ] Criar `SecurityConfig.java`
- [ ] Implementar autenticação básica
- [ ] Configurar autorização por roles
- [ ] Proteger endpoints sensíveis:
  - [ ] Operações bancárias (depósito, saque, transferência)
  - [ ] Consulta de saldo
  - [ ] Histórico de transações
- [ ] Configurar CORS se necessário

### 4.2 Implementar integração com APIs externas
**Status:** 🔴 Pendente
- [ ] Configurar `RestTemplate` no projeto
- [ ] Criar `IntegracaoExternaService.java`
- [ ] Simular APIs externas:
  - [ ] API de consulta de CPF/CNPJ
  - [ ] API de transferência interbancária
  - [ ] API de pagamentos externos
- [ ] Implementar tratamento de erros de integração
- [ ] Adicionar logs de auditoria para integrações

---

## 🛠️ FASE 5: MELHORIAS E QUALIDADE
*Prioridade: BAIXA | Estimativa: 6-8 horas*

### 5.1 Expandir GlobalExceptionHandler
**Status:** 🔴 Pendente
- [ ] Criar exceções específicas:
  - [ ] `SaldoInsuficienteException`
  - [ ] `ContaInativaException`
  - [ ] `LimiteExcedidoException`
  - [ ] `ChavePixInvalidaException`
- [ ] Adicionar handlers para novas exceções
- [ ] Melhorar mensagens de erro
- [ ] Adicionar códigos de erro padronizados

### 5.2 Criar testes unitários
**Status:** 🔴 Pendente
- [ ] Testes para `PessoaFisicaService`
- [ ] Testes para `PessoaJuridicaService`
- [ ] Testes para `ContaBancariaService`
- [ ] Testes para operações bancárias:
  - [ ] Depósito
  - [ ] Saque
  - [ ] Transferência
  - [ ] PIX
- [ ] Testes para Controllers
- [ ] Configurar cobertura de testes mínima (80%)

### 5.3 Criar documentação da API
**Status:** 🔴 Pendente
- [ ] Adicionar dependência Swagger/OpenAPI no `pom.xml`
- [ ] Configurar `OpenApiConfig.java`
- [ ] Adicionar anotações nos Controllers:
  - [ ] `@Operation` para cada endpoint
  - [ ] `@ApiResponse` para respostas
  - [ ] `@Schema` para DTOs
- [ ] Documentar códigos de erro
- [ ] Criar exemplos de requisições/respostas

---

## 📈 CRONOGRAMA SUGERIDO

### Semana 1: Fase 1
- Dias 1-2: PessoaJuridica CRUD
- Dias 3-4: ContaBancaria CRUD
- Dia 5: Validações CPF/CNPJ

### Semana 2: Fase 2 (Parte 1)
- Dias 1-2: Depósito e Saque
- Dias 3-4: Transferência
- Dia 5: PIX

### Semana 3: Fase 2 (Parte 2) + Fase 3
- Dias 1-2: Finalizar operações bancárias
- Dias 3-4: Consultas e relatórios
- Dia 5: Testes e ajustes

### Semana 4: Fases 4 e 5
- Dias 1-2: Spring Security
- Dias 3-4: Integrações externas
- Dia 5: Melhorias e documentação

---

## 🎯 CRITÉRIOS DE ACEITAÇÃO

### Para cada operação bancária:
- [ ] Validações de entrada implementadas
- [ ] Atualização correta do saldo
- [ ] Registro da transação no histórico
- [ ] Tratamento de erros adequado
- [ ] Testes unitários com cobertura mínima
- [ ] Documentação da API atualizada

### Para segurança:
- [ ] Endpoints protegidos por autenticação
- [ ] Autorização por roles implementada
- [ ] Logs de auditoria para operações sensíveis
- [ ] Tratamento seguro de dados sensíveis

---

## 📝 NOTAS IMPORTANTES

1. **Ordem de implementação**: Respeitar as dependências entre as fases
2. **Testes**: Implementar testes junto com cada feature
3. **Documentação**: Manter documentação atualizada
4. **Logs**: Adicionar logs importantes para auditoria
5. **Validações**: Implementar validações robustas desde o início
6. **Performance**: Considerar paginação para listagens grandes
7. **Segurança**: Não expor dados sensíveis em logs ou respostas

---

*Última atualização: $(date)*
*Versão do plano: 1.0*
