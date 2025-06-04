# Sala-Livre-Projeto

## Objetivo e Público-Alvo

Esta API tem como objetivo gerenciar o agendamento de salas e recursos compartilhados em uma instituição, como laboratórios, salas de reunião e equipamentos. Ela é destinada a usuários e administradores que precisam reservar e administrar esses recursos de forma segura e eficiente.

---

## Funcionalidades Implementadas

- **Cadastro de Usuários:** Usuários podem se registrar com nome, e-mail e senha.
- **Login e Autenticação:** Usuários autenticam-se via login e recebem token JWT para acessar a API.
- **Controle de Papéis:** Usuários podem ter papel USER ou ADMIN, definindo suas permissões.
- **Gerenciamento de Salas/Recursos (Admin):** Administradores podem cadastrar, editar, excluir e listar recursos.
- **Visualização de Recursos:** Usuários autenticados podem consultar todos os recursos disponíveis.
- **Reservas:** Usuários autenticados podem agendar recursos para datas e horários específicos, com validação para evitar conflitos.
- **Gerenciamento de Reservas:** Usuários podem listar suas reservas futuras e passadas, além de cancelar reservas futuras.
- **Controle de Reservas (Admin):** Administradores podem listar todas as reservas e excluir reservas conflitantes ou inválidas.
- **Filtros de Consulta:** Pesquisa de reservas por sala, data, horário e usuário.

---

## Instruções para Execução Local

### Pré-requisitos

- Java 17 (ou superior)
- Maven 3.6+
- MySQL Server rodando localmente ou remotamente
- IDE recomendada: IntelliJ IDEA, Eclipse, VSCode

### Configuração do Banco de Dados

1. Crie um banco no MySQL, por exemplo: `reservas_db`.
2. Configure as credenciais e URL no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reservas_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
