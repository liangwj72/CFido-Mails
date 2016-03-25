@echo off
@setlocal

set RUNTIME_DIR=runtime

if not exist "%RUNTIME_DIR%" (
	echo 创建运行目录 %RUNTIME_DIR%
	md "%RUNTIME_DIR%"
)