#!/bin/bash

# 进入脚本所在目录
workspace=$(cd $(dirname $0) && pwd -P)
cd $workspace
echo "工作目录: ${PWD}"

# 加载环境变量
source ./env-docker.sh

# 查看maven编译输出的内容
if [ -z "${SUB_POM}" ]; then 
    export SUB_POM="."
fi
export TARGET_PATH="../target/${SUB_POM}"
echo "编译后的目录: ${TARGET_PATH} 下的内容："
ls -l ${TARGET_PATH}

# 检查相关文件是否存在
if [ -z ${JAR_FILE} ]; then
    echo "请设置环境变量 JAR_FILE jar文件名"
    exit 1
fi
if [ ! -f ${TARGET_PATH}/${JAR_FILE} ]; then
    echo "找不到jar文件 ${JAR_FILE}"
    exit 1
fi

if [ ! -d ${TARGET_PATH}/lib ]; then
    echo "找不到lib目录"
    exit 1
fi

#创建临时目录
rm -rf dist
mkdir -p dist

# 拷贝maven build的文件过来
rsync -a ${TARGET_PATH}/${JAR_FILE} dist/
rsync -a --delete ${TARGET_PATH}/lib dist/
rsync -a application-template.yml dist/
rsync -a lib.create.application.sh dist/

# 生成启动文件
cat > dist/entrypoint.sh <<EOF
#!/bin/bash

# 脚本出错就退出
set -e

# 使用环境变量替换掉配置文件中的内容
export IMG_NAME=${IMG_NAME}

source ./lib.create.application.sh
createApplication

# 查看当前环境变量
env

# 启动
java -jar ${JAR_FILE} ${JAVA_OPS}
EOF
chmod a+x dist/*.sh

#docker构建
echo "------------------------"
sudo docker build -t ${IMG_NAME} .

# 删除临时目录
rm -rf dist

echo "------------------------"
echo "${IMG_NAME} 构建完成"

# 删除中间镜像
dangling=$(sudo docker images --filter dangling=true -q)
if [ ! -z ${dangling} ]; then
    echo "构建删除中间镜像 ${dangling}"
    sudo docker rmi ${dangling}
fi
#sudo docker images | grep "<none>" | awk '{print $3}' | xargs sudo docker rmi
