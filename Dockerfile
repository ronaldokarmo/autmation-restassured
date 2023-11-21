#Contruir imagem
#    docker build -t <IMAGE-NAME> -f ./Dockerfile .
#Rodar os testes
#    docker run -v "$PWD/target:/usr/target" <IMAGE-NAME>:latest mvn test "-Dcucumber.options=--tags '@login'" -Denv=des
FROM maven:3.6.0-jdk-11-slim
WORKDIR /usr
COPY . /usr
ENV TZ=America/Sao_Paulo
RUN mvn dependency:go-offline -B