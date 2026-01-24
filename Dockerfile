FROM {IMAGE}
COPY target/student-management-0.0.1-SNAPSHOT.jar student.jar
EXPOSE 8089
ENTRYPOINT [ "java", "-jar", "/student.jar" ]