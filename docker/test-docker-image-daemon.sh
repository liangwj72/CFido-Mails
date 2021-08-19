# 已后台方式启动镜像
source ./env-docker.sh

echo "启动镜像"
sudo docker run -p 8080:8080 -itd --rm ${IMG_NAME} 

containerId=`sudo docker ps | grep ${IMG_NAME} | awk '{print $1}'`
echo "容器ID: ${containerId}"

echo "进入容器 ${containerId}"
sudo docker exec -it  ${containerId} /bin/bash;

echo "正在停止容器: ${containerId}"
sudo docker stop ${containerId}

echo "复核所有的容器"
sleep 1
sudo docker ps -a