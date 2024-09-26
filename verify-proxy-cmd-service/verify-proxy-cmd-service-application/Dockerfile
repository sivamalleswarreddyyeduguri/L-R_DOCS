FROM default-quay-registry.apps.blr-hub.ubidp.local/nonproduat/ubi8-openjdk17:1.20-3.1723644089
WORKDIR /work/
USER root
RUN chown 1001 /work && chmod 'g+rwX' /work  && chown 1001:root /work
COPY deployments/*.jar app.jar
USER 1001
ENTRYPOINT ["java","-jar","app.jar"]
