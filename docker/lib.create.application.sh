# 创建 application.yaml文件
function createApplication () {

    # 根据环境变量选择模板
    # application-template.yml

    if [ -z "${OUT_PATH}" ]; then
        export OUT_PATH="."
    fi

    # 使用环境变量替换掉配置文件中的内容
    envsubst < application-template.yml > ${OUT_PATH}/application.yml
    echo "生产配置文件 ${PWD}/${OUT_PATH}/application.yml"

}
