#!/bin/bash

# Check if at least one service name is provided
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

  # Navigate to the service directory and run the Maven command
  cd "$SERVICE_DIR"
  ./mvnw clean compile install -DskipTests

  # Check if the Maven command was successful
  if [ $? -eq 0 ]; then
    echo "Build successful for service: $SERVICE_NAME"
  else
    echo "Build failed for service: $SERVICE_NAME"
  fi
  
  cd ..
done