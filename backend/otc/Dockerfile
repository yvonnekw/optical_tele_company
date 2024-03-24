# Base image
FROM tomcat:jdk17

# Remove existing ROOT application
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file into the Tomcat webapps directory
COPY target/otc-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8000
# Start Tomcat
CMD ["catalina.sh", "run"]