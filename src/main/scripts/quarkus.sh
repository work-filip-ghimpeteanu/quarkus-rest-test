#!/usr/bin/env bash

# Colors for pretty printing: https://misc.flogisoft.com/bash/tip_colors_and_formatting
BLUE="\e[34m"
NC="\e[39m" # No Color

COMMANDS=("build" "start" "stop" "cleanup")

########################################################################################################################
######                                                  SCRIPT'S STEPS
########################################################################################################################
function doDockerCleanUp() {
    docker rmi $(docker image ls | grep quarkus | awk '{print $3}')
    docker rmi registry.access.redhat.com/ubi8/ubi-minimal:8.1
}

function doBuild() {
  if [[ "$1" == "native" ]]
  then
    echo -e "${BLUE}-- Building native quarkus${NC}"
    mvn package -Pnative -Dquarkus.native.container-build=true && \
    docker build -f src/main/docker/Dockerfile.native -t quarkus/quarkus-rest-test:native .
  else
    echo -e "${BLUE}-- Building JVM quarkus${NC}"
    mvn package && \
    docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-rest-test:jvm .
  fi
}

function doStart() {
  env -C src/main/docker docker-compose up -d
}

function doStop() {
  env -C src/main/docker docker-compose down
}

########################################################################################################################
######                                                  HELPER FUNCTIONS
########################################################################################################################
function logUsage() {
  script_name=$(sed 's|/.*/||g' <<< ${0})
  echo "Usage: ${script_name} $(printCommands)"
}

function printCommands() {
  echo "[${COMMANDS[@]}]" | tr -s ' ' '|'
}

########################################################################################################################
######                                                 SCRIPT LOGIC
########################################################################################################################
if [[ ${1} == "start" ]]
then
  echo -e "${BLUE}##################### STARTING ENVIRONMENT #####################${NC}"
  doStop
  doStart
  echo -e "${BLUE}##################### DONE #####################${NC}"
elif [[ ${1} == "build" ]]
then
  echo -e "${BLUE}##################### BUILDING ENVIRONMENT #####################${NC}"
  doStop
  doBuild ${2}
  echo -e "${BLUE}##################### DONE #####################${NC}"
elif [[ ${1} == "stop" ]]
then
  echo -e "${BLUE}##################### CLOSING ENVIRONMENT #####################${NC}"
  doStop
  echo -e "${BLUE}##################### DONE #####################${NC}"
elif [[ ${1} == "cleanup" ]]
then
  echo -e "${BLUE}##################### CLEANING ENVIRONMENT #####################${NC}"
  doDockerCleanUp
  echo -e "${BLUE}##################### DONE #####################${NC}"
else
  logUsage ${@}
fi
