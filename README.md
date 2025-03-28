# Orchestration acceptance tests

These are selenium tests written to run during the deployment pipeline. 
They cover the most critical interactions of Orchestration and are the tip of the testing pyramid.


## Running locally
Prerequisites:
- GDS VPN
- Docker
- AWS CLI
- AWS profiles set up per https://github.com/govuk-one-login/authentication-api/blob/main/scripts/set-up-sso.sh
- Docker Desktop v4.25+, with `Use Rosetta for x86_64/amd64 emulation on Apple Silicon` turned on in settings

To log into the correct AWS account, run
```
export AWS_PROFILE=di-orchestration-build-admin
aws sso login
```

You can then run `./run-local.sh`.

## Running locally with local environment
Prerequisites:
- An account on build (go to https://rp-build.build.stubs.account.gov.uk/ to make one)
- Docker
- Docker Desktop v4.25+, with `Use Rosetta for x86_64/amd64 emulation on Apple Silicon` turned on in settings

Copy the `.env.sample` file to `.env` and add the credentials of a valid test account. 

Note that the identity tests expect that a test account is used, which uses a fixed OTP. The easiest way to run these locally is to get the credentials and the fixed OTP that the acceptance tests use from AWS, and set them in the `.env` file.

You can then run `./local-env/run-local-env.sh`. This will run the tests with your own environment variables.

## Running in the pipeline
A GitHub action builds the container image and pushes it to an ECR repository in the Build account upon push to main.
This is referenced from the Orchestration API deploy pipeline, defined at https://github.com/govuk-one-login/authentication-api/tree/main/ci/stack-orchestration/configuration/build/build-orch-be-pipeline.

The documentation [here](https://govukverify.atlassian.net/wiki/spaces/PLAT/pages/3054010402/How+to+run+tests+against+your+deployed+application+in+a+SAM+deployment+pipeline)
describes the requirements of the image and how it is invoked. The full pipeline definition can be found at https://github.com/govuk-one-login/devplatform-deploy/blob/main/sam-deploy-pipeline/template.yaml.

### Configuration
These run in the Orchestration Build account. They fetch their configuration from SSM parameters in that account. The names of these parameters have the prefix `/tests/build-orch-be-deploy/`, as per https://github.com/govuk-one-login/orchestration-acceptance-tests/blob/main/run-tests.sh#L8C67-L8C95

The pipeline is configured to produce a test report, which is the best place to look for details of any failures. These reports can be a little tricky to find, so the #di-orch-developers slack channel has a bookmark to them at the top.
