#!/bin/bash

set -euo pipefail

function get_env_vars() {
  set -a
  source ./.env
  set +a
}

cd /test

# Start the selenium server
/opt/bin/entry_point.sh &

get_env_vars

TEST_EXIT_CODE=0
./gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64 cucumber || TEST_EXIT_CODE=$?

cp /test/acceptance-tests/target/cucumber-report/report.json ${TEST_REPORT_ABSOLUTE_DIR:-/test}/report.json

exit $TEST_EXIT_CODE