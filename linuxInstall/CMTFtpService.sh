#!/bin/bash
# description: CMTFtpService
# processname: CMTFtpService
# chkconfig: 234 20 80

# 程序名稱
SERVICE_NAME=CMTFtpService

# working directory
APP_HOME=$(pwd)
EXEC=/opt/jsvc/commons-daemon-1.2.2-src/src/native/unix/jsvc
#JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.el6_10.x86_64
JAVA_HOME=/usr/lib/jvm/java-1.8.0

#依賴路徑
CLASS_PATH=FtpService-1.0-all.jar

#程序入口類
CLASS=com.iisi.cmt.ftpService.LinuxDaemonMainClass

#程序ID文件
PID=${SERVICE_NAME}.pid
#日誌輸出路徑
LOG_OUT=logs/${SERVICE_NAME}.out
LOG_ERR=logs/${SERVICE_NAME}.err

#輸出
do_print()
{
	echo "service name: $SERVICE_NAME"
	echo "app home: $APP_HOME"
	echo "jsvc: $EXEC"
	echo "java home: $JAVA_HOME"
	echo "class path: $CLASS_PATH"
	echo "main class: $CLASS"
}

#執行
do_exec()
{
	$EXEC -home "$JAVA_HOME" \
			-cwd /opt/CMTFtpService \
			-Xms512M -Xmx1024M \
			-cp $CLASS_PATH \
			-outfile $LOG_OUT \
			-errfile $LOG_ERR \
			-debug \
#			-user root \
			-pidfile $PID $1 $CLASS
}

#根據參數執行
case "$1" in
    start)
        do_exec
        echo "${SERVICE_NAME} started"
            ;;
    stop)
        do_exec "-stop"
        echo "${SERVICE_NAME} stopped"
            ;;
    restart)
        if [ -f "$PID" ]; then
            do_exec "-stop"
            do_exec 
            echo "${SERVICE_NAME} restarted"
        else
			do_exec
			echo "${SERVICE_NAME} started"
        fi
            ;;
    status)
		do_print
        ps -ef | grep jsvc
        ;;
    *)
        echo "usage: service ${SERVICE_NAME} {start|stop|restart|status}" >&2
        exit 3
        ;;
esac
