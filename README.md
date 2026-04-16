# Order Factory
[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.3.3-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-Container-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)
[![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/compose/)

Sistema de restaurante desenvolvido para gerenciamento de pedidos e registro de usuários(atendentes), permitindo cadastro de entidades, listagem de cardápio, lançamento de pedidos e registro de pagamento. O sistema também conta com histórico de lançamentos, restrição de acesso para administradores e relatório financeiro. Para escalabilidade e performance, a API armazena dados em cache executado em container, tornando consultas e comandos mais ágeis.

## 🚀 Executando a API via Docker
Para iniciar OrderFactory e todas as suas dependências (como banco de dados e Redis) de forma automatizada, utilize o Docker Compose.

Na raiz do projeto, onde o arquivo docker-compose.yml está localizado, execute o seguinte comando:

````bash
docker compose --profile test up -d
````

## 🛑 Como parar a aplicação
Quando terminar seus testes, você pode derrubar os contêineres e liberar os recursos da máquina rodando:

```bash
docker compose --profile test down
```

Com a aplicação em runtime, acesse a URL da documentação:

```bash
http://localhost:9090/swagger-ui/index.html
```
## Autenticação de usuário

  <div align="center">
   <p>Quando autenticar o usuário registrado pelo "/auth/login" será retornado um token, copie e clique em "Authorize":</p>
    
  <img width="50%" alt="image" src="https://github.com/user-attachments/assets/66159a7d-ecee-4db6-bc28-91fda2b19953" />

  Em seguida cole seu token aqui:
  
  <img width=50% alt="image" src="https://github.com/user-attachments/assets/15605e33-1f6a-41a5-a019-c1a5537a40f6" />
</div>

## Cartões
Para registro de pagamento na modalidade de cartão, há cartões fictícios instanciados em memória. Confira as senhas de cada um:

Id: 1 | Senha: @#1234

Id: 2 | Senha: @#9876

Id: 3 | Senha: @#5544

Id: 4 | Senha: @#102030

Id: 5 | Senha: @#8899

Id: 6 | Senha: @#1122

Id: 7 | Senha: @#3344

Id: 8 | Senha: @#5566

Id: 9 | Senha: @#7788

Id: 10 | Senha: @#9966

 

