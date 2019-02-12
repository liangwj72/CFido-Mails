:: ------------------------
:: eclipse 中运行mvn的批处理，
:: ------------------------

:: 需要先进入项目所在目录，否则找不到pom文件
cd /d "%~dp0"

@call mvn clean install -Dmaven.test.skip=true

@pause