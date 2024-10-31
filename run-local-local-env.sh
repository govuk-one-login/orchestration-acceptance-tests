#!/bin/bash

docker build . -t orchestration-acceptance-test --file docker/local-env.Dockerfile
docker run orchestration-acceptance-test