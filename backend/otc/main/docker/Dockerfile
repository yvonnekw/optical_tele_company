#base image
FROM tomcat:jdk17 AS build

# Remove existing webapps, create directory, and copy WAR file
RUN rm -rf /usr/local/tomcat/webapps/* && \
    mkdir -p /usr/local/tomcat/webapps && \
    COPY otc-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Set the working directory
WORKDIR /usr/local/tomcat/webapps

# Start Tomcat
CMD ["catalina.sh", "run"]

FROM nginx:alpine

# Copy the built WAR file from the Tomcat image to the nginx web server
COPY --from=build /usr/local/tomcat/webapps/ROOT.war /usr/share/nginx/html