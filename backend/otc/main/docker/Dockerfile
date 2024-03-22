#base image


FROM tomcat:jdk17 AS build
RUN rm -rf /usr/local/tomcat/webapps/* \
&& WORKDIR /usr/local/tomcat/webapps   # Added backslash here
COPY otc-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]

FROM nginx:alpine
COPY --from=build /usr/local/tomcat/webapps/ROOT.war /usr/share/nginx/html