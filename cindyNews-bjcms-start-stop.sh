#端口号
last_jar_port=8010
#jar文件路径
jar_path=/usr/local/app/bjcms/bjcms-0.0.17.jar

args="--spring.profiles.active=cindyNews --server.port="$last_jar_port" --logging.file.path=/usr/local/app/bjcms_log_"$last_jar_port
start(){


# ps aux | grep bjcms-0.0.12.jar | grep -v grep | awk '{print $2}' | xargs kill -9
lsof -i:$last_jar_port | grep $last_jar_port | grep -v grep | awk '{print $2}' | xargs kill -9

nohup java -jar  $jar_path $args > $jar_path.out 2>&1 &
}
stop(){

lsof -i:$last_jar_port | grep $last_jar_port | grep -v grep | awk '{print $2}'
}
case "$1" in
  "start")
    start
    ;;
	"stop")
    stop
    ;;
  *)

esac


