@echo off
@setlocal

set RUNTIME_DIR=runtime

rem 这是版本号，请保持和pom.xml中的一致
set /p VERSION=<VERSION.TXT

set FILE=..\target\CFidoMail-%VERSION%.jar

echo =============================================
echo 拷贝文件 %FILE%
echo =============================================

IF NOT EXIST %FILE% (
	echo 找不到要拷贝的文件, 请检查：
	echo 1.是否build失败了。
	echo 2.VERSION.TXT中定义的版本号与pom.xml中定义的版本号是否一致
	goto exit
)

copy %FILE% %RUNTIME_DIR%\

IF NOT EXIST %RUNTIME_DIR%\application.properties (
	copy ..\src\main\resources\application.properties %RUNTIME_DIR%\
	echo 创建默认的配置文件	application.properties, 请修改里面数据库等参数
)

IF NOT EXIST %RUNTIME_DIR%\start.cmd (
	copy start.cmd.template %RUNTIME_DIR%\start.cmd
)


:exit
