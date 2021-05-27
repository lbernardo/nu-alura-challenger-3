FROM centos:7
RUN mkdir /var/apps
RUN yum install -y wget java-11-openjdk
RUN wget https://downloads.apache.org/kafka/2.8.0/kafka_2.13-2.8.0.tgz ; \
    tar -xzf kafka_2.13-2.8.0.tgz -C /var/apps ; \
    mv /var/apps/kafka_2.13-2.8.0 /var/apps/kafka ; \
    rm -f kafka_2.13-2.8.0.tgz
