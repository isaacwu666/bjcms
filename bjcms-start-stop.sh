start(){

 ps aux | grep bjcms.jar | grep -v grep | awk '{print $2}' | xargs kill -9

nohup java -jar  /usr/local/app/bjcms-0.0.1.jar > /usr/local/app/bjcms-0.0.1.jar.out 2>&1 &
}
stop(){

 ps aux | grep bjcms-0.0.1.jar | grep -v grep | awk '{print $2}' | xargs kill -9
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
