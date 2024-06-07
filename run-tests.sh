#!/bin/bash

set -euo pipefail

function get_env_vars_from_SSM() {

  echo "Getting environment variables from SSM ... "
  VARS="$(aws ssm get-parameters-by-path --with-decryption --path /tests/build-orch-be-deploy | jq -r '.Parameters[] | @base64')"
  for VAR in $VARS; do
    VAR_NAME="$(echo ${VAR} | base64 -d | jq -r '.Name / "/" | .[3]')"
    export "$VAR_NAME"="$(echo ${VAR} | base64 -d | jq -r '.Value')"
  done
  echo "Export SSM parameters completed."
}

cd /test

# Start the selenium server
/opt/bin/entry_point.sh &

get_env_vars_from_SSM

TEST_EXIT_CODE=0
./gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-17-openjdk-amd64 cucumber || TEST_EXIT_CODE=$?

cp /test/acceptance-tests/target/cucumber-report/report.json ${TEST_REPORT_ABSOLUTE_DIR:-/test}/report.json

exit $TEST_EXIT_CODE