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

You can then run `./run-local.sh`

## Running in the pipeline
A GitHub action builds the container image and pushes it to an ECR repository in the Build account upon push to main.
This is referenced from the Orchestration API deploy pipeline, defined at https://github.com/govuk-one-login/authentication-api/tree/main/ci/stack-orchestration/configuration/build/build-orch-be-pipeline.

The documentation [here](https://govukverify.atlassian.net/wiki/spaces/PLAT/pages/3054010402/How+to+run+tests+against+your+deployed+application+in+a+SAM+deployment+pipeline)
describes the requirements of the image and how it is invoked. The full pipeline definition can be found at https://github.com/govuk-one-login/devplatform-deploy/blob/main/sam-deploy-pipeline/template.yaml.