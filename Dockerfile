FROM houwm/jdk8

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

COPY docker-image /opt/aligenie
#RUN mkdir /opt/aligenie/config
WORKDIR /opt/aligenie

EXPOSE 2000

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /opt/aligenie/aligenie-auth2.0.jar"]

#docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug -Xmx128m" myorg/myapp
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/aligenie/aligenie-auth2.0.jar"]

#ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]
#docker run -p 9000:9000 myorg/myapp --server.port=9000
