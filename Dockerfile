FROM tomcat:9.0-jdk17-corretto

# Eliminar las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el archivo WAR compilado como ROOT.war para que la aplicación responda en la raíz (/)
COPY dist/reservahotel-servlets-jsp.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
