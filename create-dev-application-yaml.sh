
# 创建用于开发的 application.yml 文件

# 进入脚本所在目录
workspace=$(cd $(dirname $0) && pwd -P)
cd $workspace

# 加载配置参数, 将配置中的每一行都都加个 export 然后执行
$(sed 's/^/export /' ./docker/env-dev.conf)

export IMG_NAME="\"不是用docker运行\""

# 生成配置文件
export OUT_PATH="../src/main/resources"
echo "根据 env-dev.conf 的参数用生成开发用的 application.yml"

cd docker
source env-dev.conf
source lib.create.application.sh
createApplication