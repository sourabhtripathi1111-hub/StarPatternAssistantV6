@echo off
set GRADLE_VERSION=8.7
set GRADLE_HOME=%USERPROFILE%\.gradle\manual\gradle-%GRADLE_VERSION%
if not exist "%GRADLE_HOME%\bin\gradle.bat" (
  echo Please run the GitHub Actions workflow, or install Gradle %GRADLE_VERSION% locally.
  exit /b 1
)
"%GRADLE_HOME%\bin\gradle.bat" %*
