#!/bin/bash

env_file=$1
app_name=$2

lines=(`cat "$env_file"`)
result=""

old_ifs=$IFS
IFS='='

for param in "${lines[@]}"
do
  if [[ $param =~ "=" ]]; then
    read -a strarr <<< "$param"
    key=${strarr[0]}
    value=${strarr[1]}
    if [[ $param =~ " " ]]; then
      java_param="$key=\"$value\""
    else
      java_param="$key=$value"
    fi
    result="$result -D$java_param"
  fi
done

IFS=$old_ifs

result="${result:1}"

java -jar $result "$app_name"
