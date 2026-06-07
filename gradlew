#!/usr/bin/env bash
set -euo pipefail
GRADLE_VERSION="8.7"
GRADLE_HOME="$HOME/.gradle/manual/gradle-${GRADLE_VERSION}"
ZIP_FILE="/tmp/gradle-${GRADLE_VERSION}-bin.zip"
if [ ! -x "$GRADLE_HOME/bin/gradle" ]; then
  mkdir -p "$HOME/.gradle/manual"
  echo "Downloading Gradle ${GRADLE_VERSION}..."
  curl -L --retry 3 --connect-timeout 20 -o "$ZIP_FILE" "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
  unzip -q "$ZIP_FILE" -d "$HOME/.gradle/manual"
fi
exec "$GRADLE_HOME/bin/gradle" "$@"
