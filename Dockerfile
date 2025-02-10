FROM eclipse-temurin:21-jre-alpine

#Environment variables
ENV INSTALL_HOME=/opt/install

EXPOSE 8080 8081

ADD target/audit-secu-bbl-*.jar $INSTALL_HOME/audit-secu-bbl.jar

WORKDIR $INSTALL_HOME
CMD java -jar audit-secu-bbl.jar
