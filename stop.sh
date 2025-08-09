#!/bin/bash
# macOS double-click stop script for InMemoryAPI

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

osascript <<EOD
tell application "Terminal"
    do script "cd '$SCRIPT_DIR' && if [ -f app.pid ]; then PID=\$(cat app.pid); echo '🛑 Stopping InMemoryAPI with PID' \$PID && kill \$PID && rm app.pid && echo '✅ Application stopped.'; else echo '⚠️ No running application found (app.pid missing).'; fi"
    activate
end tell
EOD
