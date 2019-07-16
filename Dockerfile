FROM openjdk:8-jre-alpine

#Environment variables
ENV INSTALL_HOME=/opt/install

EXPOSE 8080 8081

ADD target/install-*.jar $INSTALL_HOME/install.jar

RUN chown -R app:app $INSTALL_HOME

USER 1001

WORKDIR $INSTALL_HOME
CMD java -jar install.jar -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap
