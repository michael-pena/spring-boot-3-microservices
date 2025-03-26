#!/bin/bash

# Check if at least one service name is provided

if [[ "$1" == "all" ]]; then
  docker build accounts-ms/
  docker build configserver/
  docker build customer-ms/
  docker build eurekaserver/
  docker build gatewayserver/
  docker build message/
else

  if [ $# -eq 0 ]; then
  echo "Usage: $0 <service-name1> <service-name2> ... <service-nameN>"
  exit 1
  fi

  # Loop through all provided service names
  for SERVICE_NAME in "$@"
  do
    # Define the path to the service directory
    SERVICE_DIR="./$SERVICE_NAME"

    # Check if the service directory exists
    if [ ! -d "$SERVICE_DIR" ]; then
      echo "Service directory $SERVICE_DIR does not exist."
      continue
    fi

    # Navigate to the service directory and run the docker build command
    cd "$SERVICE_DIR"
    docker build .

    # Check if the docker command was successful
    if [ $? -eq 0 ]; then
      echo "Build successful for service: $SERVICE_NAME"
    else
      echo "Build failed for service: $SERVICE_NAME"
    fi
    
    cd ..
  done
fi