#!/bin/sh

## definition
APP_NAME=zookeeper/zookeeper-3.4.11 # modify 01
SERVICE_DIR=/home/hms/$APP_NAME

## pretreatment
cd $SERVICE_DIR

case "$1" in

    start)
        ./bin/zkServer.sh $1 
        ;;

    stop)
		./bin/zkServer.sh $1
        ;;

    restart)
        ./bin/zkServer.sh $1
        ;;

    *)
        echo $0 "{start|start-foreground|stop|restart|status|upgrade|print-cmd}"
        ;;

esac
exit 0