# Source image
FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine

# Set Argument
ENV APP_NAME="sample-app-foo"
ENV APP_VERSION="v2.2"

# Create group and user to run application.
RUN addgroup -S appgroup \
 && adduser -S appuser -G appgroup -h /home/appuser

# Tell docker that all future commands should run as the appuser user
USER appuser
WORKDIR /home/appuser

# copy binary file to appuser home-directory
COPY --chown=appuser:appgroup build/libs/${APP_NAME}-${APP_VERSION}.jar /home/appuser/app.jar

# entrypoint
ENTRYPOINT [ "/opt/java/openjdk/bin/java", "-XX:NativeMemoryTracking=detail", "-XX:MetaspaceSize=128m", "-XX:MaxMetaspaceSize=128m", "-Xms1500m", "-Xmx1500m",  "-jar", "/home/appuser/app.jar" ]