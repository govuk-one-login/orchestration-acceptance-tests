#!/bin/bash

export AWS_PROFILE=di-orchestration-build-admin
aws sso login
aws cloudformation update-stack \
  --region eu-west-2 \
  --stack-name="test-container-stack" \
  --capabilities CAPABILITY_NAMED_IAM \
  --template-body file://template.yaml \
  --tags Key=Product,Value='GOV.UK Sign In' \
         Key=System,Value=Orchestration \
         Key=Environment,Value=Build \
         Key=Owner,Value='di-orchestration@digital.cabinet-office.gov.uk' \
         Key=Respository,Value='govuk-one-login/orchestration-acceptance-tests'