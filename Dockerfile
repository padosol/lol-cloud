FROM bellsoft/liberica-openjdk-alpine:17

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-DJWT_KEY=slkcjtlkwethncjkwehtnkcljwerctnhjkrwethnckjwerthclkwer","/app.jar"]