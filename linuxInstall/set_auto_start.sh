#!/bin/bash

DAEMON_HOME=$(pwd)
AUTO_RUN_FILE=${DAEMON_HOME}/CMTFtpService.sh
AUTO_RUN_FILE_NAME=CMTFtpService

echo "設置${AUTO_RUN_FILE_NAME}開機啟動......"

#若文件不存在報錯，否則設置開機啟動
if [ ! -f "${AUTO_RUN_FILE}" ]; then 
    echo "${AUTO_RUN_FILE} 不存在，請檢查文件!"
else
    \cp -f ${AUTO_RUN_FILE} /etc/init.d/${AUTO_RUN_FILE_NAME}
    chmod 777 /etc/init.d/${AUTO_RUN_FILE_NAME} 
    chkconfig --add ${AUTO_RUN_FILE_NAME} 
    chkconfig ${AUTO_RUN_FILE_NAME} on 
fi

echo "設置${AUTO_RUN_FILE_NAME}開機啟動結束"
