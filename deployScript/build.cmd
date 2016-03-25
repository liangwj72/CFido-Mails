@echo off
@setlocal

set RUNTIME_DIR=runtime

echo 检查目录"%RUNTIME_DIR%"是否存在
echo ====================================
if not exist "%RUNTIME_DIR%" (
	echo 创建运行目录 %RUNTIME_DIR%
	md "%RUNTIME_DIR%"
) else (
	echo 目录 "%RUNTIME_DIR%" 已经存在 
)
echo.

cd .. 
echo 和代码服务器同步源码
echo ====================================
echo 在服务器上，需要运行 git.exe pull "origin" dev 同步一下文件
echo.

echo 开始执行maven build，不运行test case
echo ====================================

mvn clean package -Dmaven.test.skip=true