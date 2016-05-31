# echo-microservice
#
# VERSION               0.0.1
#
FROM          centos:7
MAINTAINER    Dragos Dascalita Haut <ddascal@adobe.com>

ENV	LANG en_US.UTF-8
ENV	LC_ALL en_US.UTF-8

RUN yum install -y curl; yum upgrade -y; yum update -y;  yum clean all

ENV JDK_VERSION 8u31
ENV JDK_BUILD_VERSION b13

RUN curl -LO "http://download.oracle.com/otn-pub/java/jdk/$JDK_VERSION-$JDK_BUILD_VERSION/jdk-$JDK_VERSION-linux-x64.rpm" -H 'Cookie: oraclelicense=accept-securebackup-cookie' && rpm -i jdk-$JDK_VERSION-linux-x64.rpm; rm -f jdk-$JDK_VERSION-linux-x64.rpm; yum clean all

ENV JAVA_HOME /usr/java/default

# forward request and error logs to docker log collector
RUN mkdir -p /var/log/echo-microservice/
RUN ln -sf /dev/stdout /var/log/echo-microservice/*

# dir to write the logs into
VOLUME /var/log/echo-microservice/

# RUN cd /tmp/ && curl -LO "https://path/to/released/jar/echo-service-1.0.5.jar" && mkdir -p /usr/local/echo-microservice/ && mv gw-tracking-service*.jar /usr/local/echo-microservice/echo-microservice.jar
# NOTE: ONLY WORKS FOR LOCAL DEV, AFTER RUNNING THE MAVEN BUILD
RUN mkdir -p /usr/local/echo-microservice/
COPY ./target/echo-service-*.jar /usr/local/echo-microservice/echo-microservice.jar

# this volume is used for local development
VOLUME /usr/local/echo-microservice

ENTRYPOINT ["java", "-jar", "/usr/local/echo-microservice/echo-microservice.jar"]