#!/bin/sh
## java env

APP_NAME=pyod-platform

JVM=" -Xms512m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Xloggc:./$APP_NAME/logs/gc.log"

JAR_NAME=$APP_NAME\.jar
#PID  代表是PID文件
PID=$APP_NAME\.pid

#使用说明,用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status|log|backup]"
    exit 1
}

#检查程序是否在运行
is_exist() {
  pid=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}')
  #如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
    return 1
  else
    return 0
  fi
}

#启动方法
start() {
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
    nohup java $JVM -jar $JAR_NAME >/dev/null 2>&1 &
    echo $! >$PID
    echo ">>> start $JAR_NAME successed PID=$! <<<"
  fi
}

#停止方法
stop() {
  pidf=$(cat $PID)
  #echo "$pidf"
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9 $pid
    sleep 2
    echo ">>> $JAR_NAME process stopped <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#输出运行状态
status() {
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#重启
restart() {
  stop
  start
}

#日志
log() {
  # 输出实时日志
  tail -f ./$APP_NAME/logs/debug.log
}

#备份
backup(){
      #根据需求自定义备份文件路径。
  BACKUP_PATH=/pengyi/project/$APP_NAME
      #获取当前时间作为备份文件名
  BACKUP_DATE=`date +"%Y%m%d(%H:%M:%S)"`
  echo 'backup file ->'$BACKUP_PATH$BACKUP_DATE'.jar'
      #备份当前jar包
  cp -r /pengyi/project/$JAR_NAME $BACKUP_PATH$BACKUP_DATE'.jar'
}

#根据输入参数,选择执行对应方法,不输入则执行使用说明
case "$1" in
"start")
  start
  ;;
"stop")
  stop
  ;;
"status")
  status
  ;;
"restart")
  restart
  ;;
"log")
  log
  ;;
"backup")
  backup
  ;;
*)
  usage
  ;;
esac
exit 0
