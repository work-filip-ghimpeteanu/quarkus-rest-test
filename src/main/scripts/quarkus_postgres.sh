#!/usr/bin/env bash

# Colors for pretty printing: https://misc.flogisoft.com/bash/tip_colors_and_formatting
BLUE="\e[34m"
NC="\e[39m" # No Color

COMMANDS=("start" "stop")

########################################################################################################################
######                                                  SCRIPT'S STEPS
########################################################################################################################
function doDockerCleanUp() {
    docker ps -q -a -f ancestor=postgres | xargs -r docker rm -f
}

function doDockerStart() {
  docker run -d --hostname quarkus-postgres --name quarkus-postgres -p 25432:5432 -e POSTGRES_USER=admin \
    -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=quarkus_db postgres
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
  doDockerCleanUp
  doDockerStart
  echo -e "${BLUE}##################### DONE #####################${NC}"
elif [[ ${1} == "stop" ]]
then
  echo -e "${BLUE}##################### CLOSING ENVIRONMENT #####################${NC}"
  doDockerCleanUp
  echo -e "${BLUE}##################### DONE #####################${NC}"
else
  logUsage ${@}
fi
