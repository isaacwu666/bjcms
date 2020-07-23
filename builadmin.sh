SERVER_JAR_PATH=/usr/local/app/bjcms
SERVER_HOST="203.195.242.69"
SERVER_NAME=bjcms_8010.server
SERVER_JAR_AEGS= -spring.profiles.active=prod


#重新编译后台
function build_admin() {
    pwd
    cd ./admin
    pwd
    npm run build
    echo 编译完成开始移动目录
    rm -rf ../src/main/resources/static/admin/*
    ls ../src/main/resources/static/admin/
    mv ./dist/* ../src/main/resources/static/admin/
    echo 后台管理admin编译并重新复制到springboot目录完成
    cd ../
    return 0;
}
#生成jar包
function maven_buil_jar() {
    echo 编译jar包
    mvn clean compile
    echo 生成jar包
    mvn clean package

}
#找出jar包
function find_jar() {
     echo 找出jar包

     if [ -L $0 ]
      then
          BASE_DIR=`dirname $(readlink $0)`
      else
          BASE_DIR=`dirname $0`
      fi
      basepath=$(cd $BASE_DIR; pwd)
      echo $basepath


      for file in `ls $basepath/target`
          do
                  if [ -f $basepath/target/$file ]
                    then
                          if  [[ $file =~ ".jar" ]]
                               then
#                                   echo $file"包含"
                                   if [[ $file =~ ".original" ]]; then

#                                        echo $file 不是要找的jar包
                                        echo
                                        else
#                                             echo $file 是要找的jar包
#                                             jar包在本地的绝对路径
                                             jar_path=$basepath/target/$file
#                                             jar包名字
                                             jar_name=$file

                                   fi

                               fi
                  fi
          done
#    /Users/OSX/Project/bjcms


}
#function create_server_service_file() {
#EOF
#
#echo  [Unit]
#echo     Description=SERVER_JAR_PATH
#echo   [Service]
#echo   Type=forking
#echo   ExecStart=java -jar $SERVER_JAR_PATH$jar_name
#
#
#echo   [Install]
#
#
#EOR>>$basepath/target/$SERVER_NAME
#}

#上传到服务器 文件路径为 jar
function upload_to_server() {
    ssh root@$SERVER_HOST "mv "$SERVER_JAR_PATH/$jar_name" "$SERVER_JAR_PATH/$jar_name.bk
    scp $jar_path root@$SERVER_HOST:$SERVER_JAR_PATH

#
# scp /Users/OSX/Project/bjcms/target/bjcms-0.0.14.jar root@$SERVER_HOST:SERVER_JAR_PATH
}

echo 开始

build_admin
#编译jar包
maven_buil_jar
#寻找到jar包
find_jar
#创建服务文件
#create_server_service_file

#echo 返回的jarpath $jar_path
#根据得到的$jar_path 生成服务器端文件
upload_to_server