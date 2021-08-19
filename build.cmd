cd /d "%~dp0"

@call mvn clean install -Dmaven.test.skip=true

@pause