# Etapa de construção com Maven
FROM maven:3.8.4-openjdk-17 AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando apenas o arquivo pom.xml para otimizar cache de dependências
COPY pom.xml .

# Baixando as dependências do Maven
RUN mvn dependency:go-offline

# Copiando o restante do código fonte do projeto
COPY src ./src

# Compilando o projeto
RUN mvn clean install

# Etapa de execução com JRE
FROM eclipse-temurin:17-jre

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o jar construído na etapa anterior
COPY --from=build /app/target/sglp_api-0.0.1-SNAPSHOT.jar /app/app.jar

# Expondo a porta 8080 para a aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]

# Preparação para testes (a ser usado no futuro)
# Adicione um estágio de teste que roda os testes
# FROM build AS test
# RUN mvn test