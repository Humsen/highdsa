#!/bin/sh

## definition
APP_NAME=logstash/logstash-5.6.9 # modify 01
SERVICE_DIR=/home/hms/elk/$APP_NAME # modify 02
SERVICE_NAME=logstash-logs 
PID=$SERVICE_NAME\.pid

## pretreatment
cd $SERVICE_DIR

case "$1" in

    start)
		echo "--- starting logstash ..." # modify 03
        nohup ./bin/logstash -f ../config/logstash.conf >/dev/null 2>&1 & # modify 04
        echo $! > $SERVICE_DIR/$PID
        echo "--- start $SERVICE_NAME success" 
        ;;

    stop)
		echo "--- stoping logstash ..." # modify 05
        kill `cat $SERVICE_DIR/$PID`
        rm -rf $SERVICE_DIR/$PID
        echo "--- stop $SERVICE_NAME, and wait 5s ..." 

        sleep 5s	
        P_ID=`ps -ef | grep -w logstash | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "--- $SERVICE_NAME process not exists or stop success"
        else
            echo "--- $SERVICE_NAME process pid is:$P_ID"
            echo "--- begin force kill $SERVICE_NAME process, pid is:$P_ID"
            kill -9 $P_ID
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "--- restart $SERVICE_NAME"
        ;;

    *)
        ## restart
        $0 stop
        sleep 2
        $0 start
        ;;

esac
exit 0