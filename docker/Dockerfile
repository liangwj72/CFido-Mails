FROM openjdk:8-jre

ENV APP_DB_HOST=192.168.31.105
ENV APP_DB_PORT=3306
ENV APP_DB_NAME=cfido
ENV APP_DB_USERNAME=cfido
ENV APP_DB_PASSWORD=newmoon
ENV APP_DB_DDL_AUTO=update

ENV APP_LOG_LEVEL=info
ENV APP_SHOW_SQL=false

RUN apt update

# 时区为中国
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 安装 envsubst
RUN apt -y install gettext-base curl telnet inetutils-ping net-tools procps

# 拷贝文件
WORKDIR /opt/app
COPY dist/ .

# 复核
RUN ls -l .

EXPOSE 8080

CMD /bin/bash entrypoint.sh
