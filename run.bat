@echo off
set BACKEND_DIR=backend
set FRONTEND_DIR=frontend

echo 🧠 Iniciando Freedom Forever...

echo 🚀 Iniciando backend (Spring Boot)...
start cmd /k "cd /d %BACKEND_DIR% && mvnw spring-boot:run"

timeout /t 5

echo 🌐 Iniciando frontend (React)...
cd /d %FRONTEND_DIR%
call npm install
call npm start