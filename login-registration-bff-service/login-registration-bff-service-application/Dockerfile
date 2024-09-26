FROM registry.access.redhat.com/ubi8/openjdk-17:1.17-1.1696520325
WORKDIR /work/
USER root
RUN chown 1001 /work && chmod 'g+rwX' /work  && chown 1001:root /work
COPY deployments/*.jar app.jar
USER 1001
ENTRYPOINT ["java","-jar","app.jar"]
#test
