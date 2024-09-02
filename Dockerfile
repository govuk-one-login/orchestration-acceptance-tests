FROM selenium/standalone-chrome:128.0-20240830

RUN sudo apt-get update && \
    sudo apt-get install -y openjdk-17-jdk awscli && \
    sudo rm -rf /var/lib/apt/lists/*

COPY . /test
RUN sudo chmod -R a+rwx /test && sudo mv /test/run-tests.sh /run-tests.sh
WORKDIR /test
RUN /test/gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64 clean build :acceptance-tests:compileTestJava -x :acceptance-tests:test
WORKDIR /

CMD ["/run-tests.sh"]
