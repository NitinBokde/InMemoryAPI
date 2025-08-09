@echo off
title 🛑 Stopping InMemoryAPI
echo.
cd /d %~dp0

if exist app.pid (
    set /p PID=<app.pid
    echo 🛑 Stopping application with PID %PID%...
    taskkill /PID %PID% /F
    del app.pid
    echo ✅ Application stopped.
) else (
    echo ⚠️ No app.pid file found. Trying to kill process on port 8080...
    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do taskkill /PID %%a /F
)

echo.
pause
