# Dockerfile para Sistema de Controle de Estoque

# Estágio 1: Build da aplicação
FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Compilar e empacotar a aplicação
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final com Tomcat
FROM tomcat:9.0-jre11-openjdk-slim

# Remover aplicações padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar o WAR gerado para o Tomcat
COPY --from=build /app/target/sistema-estoque-1.0.0.war /usr/local/tomcat/webapps/ROOT.war

# Expor a porta 8080
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]

