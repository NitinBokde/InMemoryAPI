#!/bin/bash
# macOS double-click startup script for InMemoryAPI

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

osascript <<EOD
tell application "Terminal"
    do script "cd '$SCRIPT_DIR' && echo '🚀 Starting InMemoryAPI...' && mvn clean install && java -jar target/inmemoryapi-0.0.1-SNAPSHOT.jar & echo \$! > app.pid && echo '✅ Application started with PID ' \$(cat app.pid)"
    activate
end tell
EOD
