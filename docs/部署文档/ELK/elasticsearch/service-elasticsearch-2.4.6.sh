#!/bin/sh

## java env
export JAVA_HOME=/home/husen/jdk/jdk1.8.0_151
export JRE_HOME=${JAVA_HOME}/jre  
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib  
export PATH=${JAVA_HOME}/bin:$PATH  

## definition
APP_NAME=elasticsearch/elasticsearch-2.4.6 # modify 01
SERVICE_DIR=/home/husen/$APP_NAME
SERVICE_NAME=elasticsearch-master # modify 02
PID=$SERVICE_NAME\.pid

## pretreatment
cd $SERVICE_DIR

case "$1" in

    start)
		echo "--- starting elasticsearch ..." # modify 03
        ./bin/elasticsearch -d
        echo $! > $SERVICE_DIR/$PID
        echo "--- start $SERVICE_NAME success" 
        ;;

    stop)
		echo "--- stoping elasticsearch ..." # modify 05
        kill `cat $SERVICE_DIR/$PID`
        rm -rf $SERVICE_DIR/$PID
        echo "--- stop $SERVICE_NAME, and wait 5s ..."

        sleep 5s	
        P_ID=`ps -ef | grep -w elasticsearch | grep -v "grep" | awk '{print $2}'`
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