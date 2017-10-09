FROM java

MAINTAINER kyyee "kyyee.com"

# Copy the resources into the container
ADD json/ json/

# Copy the files into the container
ADD wx-jssdk-sign-0.0.1.jar app.jar

# start the java application
ENTRYPOINT ["java", "-jar", "/app.jar"]
