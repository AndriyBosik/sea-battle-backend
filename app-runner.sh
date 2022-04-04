#!/bin/bash

env_file=$1
app_name=$2

lines=(`cat "$env_file"`)
result=""

for param in "${lines[@]}"
do
  result="$result -D$param"
done

result="${result:1}"

java -jar $result "$app_name"
