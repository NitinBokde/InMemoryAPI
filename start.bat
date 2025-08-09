@echo off
title 🚀 Starting InMemoryAPI
echo.
echo 🚀 Starting InMemoryAPI...

rem Go to the script's directory
cd /d %~dp0

rem Build the project
mvn clean install

rem Start the application in background and save the PID
start /B java -jar target\inmemoryapi-0.0.1-SNAPSHOT.jar
for /f "tokens=2 delims=," %%a in ('tasklist /FI "IMAGENAME eq java.exe" /FO CSV /NH') do (
    echo %%~a > app.pid
)

echo ✅ Application started. PID: 
type app.pid
echo.
pause
