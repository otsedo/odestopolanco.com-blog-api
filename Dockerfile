FROM azul/zulu-openjdk:17.0.8
WORKDIR /
ADD target/odestopolancoblogapi-0.0.1-SNAPSHOT.jar app.jar

RUN groupadd -r ODESTO &&\
    useradd -m ODESTO -g ODESTO &&\
    mkdir /files &&\
    chown -R ODESTO:ODESTO /files &&\
    su - ODESTO

ENV TZ=America/New_York
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD ["java","-jar","-Dspring.profiles.active=dev","app.jar"]