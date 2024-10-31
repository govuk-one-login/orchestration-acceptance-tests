#!/bin/bash

docker build . -t orchestration-acceptance-test --file docker/build-env.Dockerfile
eval $(aws configure export-credentials --profile di-orchestration-build-admin --format env)
docker run \
  -e AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID \
  -e AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
  -e AWS_SESSION_TOKEN=$AWS_SESSION_TOKEN \
  -e AWS_CREDENTIAL_EXPIRATION=$AWS_CREDENTIAL_EXPIRATION \
  -e AWS_DEFAULT_REGION=eu-west-2 \
  orchestration-acceptance-test