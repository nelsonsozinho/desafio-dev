# CNAB Backend


# Detalhes sobre o projeto 
- Frameworks utilizados: 
  - Spring Boot
  - Spring Data
  - JWT com jsonwebtokent jjtw
  - Kotlin 
  - Junit Jupiter 
  - MockAPI
  - testcontainers
- Banco de dados
  - Postgres
- Containers
  - Docker 
  - Docker Composer 

# Processo para executar standalone
1. gradle build
2. docker-compose build
3docker-compose up ou docker-compose up -d (instâncias em backgroud)

# Alternatia
- O projeto foi criado com Postgres e gradle. Caso queira executar pela CLI ou IDE segue os steps:
  1. O usuário utilizado para o banco de dados é o postgres, configirado com a senha postgres.
  2. É necessário criar um banco de dados com o nome de ktl-cnab
  3. Apos isso vc pode executar as migrations direto da CLI com o comando gradle flywayMigrate. Lembrando que isso é opcional, já que as migrations são executadas no momento do build
  4. Após isso é só executar o comando gradle bootRun

# APIS disponíveis
- O projeto está utilizando swagger para documentar as APIs, portanto é possível lista-las e executa-las nessa URL http://localhost:8080/api/swagger-ui/index.html 
- Utilizei o processo de autenticação e autorização. Portanto, após a execução das migrações (automativas), o usuário criado é esse:
  - username: firstuser@gmail.com
  - password: firstuser
- Caso queira criar outro usuário utilize a API para disponível para isso. 