FROM amazoncorretto:17.0.8

ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
COPY wait-for-it.sh wait.sh

RUN yum -y update
ENTRYPOINT ["bash", "-c"]
CMD ["/wait.sh mysql_db:3306 -s -t 100 -- java -jar /app.jar"]