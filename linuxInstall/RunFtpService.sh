#!/bin/bash
JAVA_HOME=/
APP_DIR=/
JAR_NAME=FtpService-1.0.jar

SERVICE=FtpService
SERVICE_FILE=RunFtpService.sh

startJar(){
	$JAVA_HOME -jar $JAR_NAME
}

stopJar(){
	kill $(ps aux | grep $SERVICE | grep -v 'grep' | grep -v $SERVICE_FILE | awk '{print $2}')
}

processExists(){
	if ps aux | grep $SERVICE | grep -v 'grep'| grep -v $SERVICE_FILE
		then 
			echo "process exists"
			return 0
		else 
			echo "process does not exist"
			return 1
	fi
}

cd $APP_DIR 

case "$1" in
	start)
		if processExists
		then
			echo "$SERVICE is already running..."
		else
			startJar
			echo "$SERVICE started."
		fi
		;;
	stop)
		if processExists
		then
			stopJar
			echo "$SERVICE stopped."
		else 
			echo "$SERVICE haven't started..."
		fi
		;;
	restart)
		if processExists
		then 
			echo "$SERVICE is running."
			echo "Restart $SERVICE."
			stopJar
			startJar
		else
			echo "Start $SERVICE."
			startJar
		fi
		;;
	status)
		if processExists
		then
			echo "$SERVICE is running."
		else
			echo "$SERVICE is not running..."
		fi
		;;
	*)
		if processExists
		then
			echo "$SERVICE is already running..."
		else
			startJar
			echo "$SERVICE started."
		fi
		;;

esac
