#!/bin/bash

docker build . -t orchestration-acceptance-test --file local-env/local-env.Dockerfile
docker run orchestration-acceptance-test