# 🕊️ Freedom Forever

Um sistema inteligente para auxiliar no tratamento de dependências e vícios, utilizando inteligência artificial (modelo LLaMA 3 via Ollama) para gerar recomendações terapêuticas personalizadas a partir das respostas dos usuários.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot 3
- Spring Data JPA
- Maven
- H2 (ou MySQL)
- Ollama + LLaMA 3
- Swagger OpenAPI
- Postman (para testes)

---

## 📦 Funcionalidades

- Cadastro e autenticação de usuários
- Questionário dinâmico sobre hábitos e dependências
- Armazenamento de respostas por usuário
- Integração com LLaMA 3 via Ollama local
- Geração automática de recomendações terapêuticas
- Histórico de progresso do usuário

---

## 🧠 Requisitos para rodar com IA (LLaMA 3)

Você deve ter o [Ollama](https://ollama.com) instalado e o modelo LLaMA 3 carregado localmente:

```bash
ollama run llama3
