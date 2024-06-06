FROM selenium/standalone-chrome:125.0-20240522

RUN sudo apt-get update && \
    sudo apt-get install -y openjdk-17-jdk awscli && \
    sudo rm -rf /var/lib/apt/lists/*

COPY . /test
RUN sudo chmod -R a+rwx /test
WORKDIR /test
RUN /test/gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64 clean build :acceptance-tests:compileTestJava -x :acceptance-tests:test

CMD ["/test/run-tests.sh"]
