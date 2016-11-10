FROM centos

RUN yum -y install java-1.8.0-openjdk

ADD analyticsSampleApplication.jar /
ADD prod.yml /
ADD qa.yml /

CMD java -jar analyticsSampleApplication.jar -yml qa.yml

EXPOSE 80
