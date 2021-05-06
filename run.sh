#!/bin/bash

declare project_dir=$(dirname $0)
declare dc_main=${project_dir}/docker-compose.yml

function restart() {
    stop_all
    start_all
}

function start() {
    echo "Starting $1...."
    build_api
    docker-compose -f ${dc_main} up --build --force-recreate -d $1
    docker-compose -f ${dc_main} logs -f
}

function stop() {
    echo "Stopping $1...."
    docker-compose -f ${dc_main} stop
    docker-compose -f ${dc_main} rm -f
}

function start_infra() {
    echo "Starting mysqldb rabbitmq setup-vault config-server service-registry hystrix-dashboard...."
    docker-compose -f ${dc_main} up --build --force-recreate -d mysqldb rabbitmq setup-vault config-server service-registry hystrix-dashboard
    docker-compose -f ${dc_main} logs -f
}

function start_all() {
    echo "Starting all services...."
    build_api
    docker-compose -f ${dc_main}  up --build --force-recreate -d
    docker-compose -f ${dc_main}  logs -f
}

function stop_all() {
    echo 'Stopping all services....'
    docker-compose -f ${dc_main} stop
    docker-compose -f ${dc_main} rm -f
}

function build_api() {
    ./mvnw clean package -DskipTests
}

action="start_all"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}