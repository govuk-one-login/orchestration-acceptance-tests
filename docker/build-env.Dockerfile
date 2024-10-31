FROM selenium/standalone-chrome:125.0-20240517

RUN sudo apt-get update && \
    sudo apt-get install -y openjdk-17-jdk awscli && \
    sudo rm -rf /var/lib/apt/lists/*

COPY . /test
RUN sudo chmod -R a+rwx /test && sudo mv /test/scripts/run-tests-build-env.sh /run-tests-build-env.sh
WORKDIR /test
RUN /test/gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64 clean build :acceptance-tests:compileTestJava -x :acceptance-tests:test
WORKDIR /

CMD ["/run-tests-build-env.sh"]
