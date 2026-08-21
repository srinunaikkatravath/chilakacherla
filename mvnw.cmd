@echo off
setlocal
if "%JAVA_HOME%" == "" (
    set "JAVA_HOME=C:\Program Files\Java\jdk-21.0.10"
)
if exist "C:\Program Files\apache-maven-3.9.12\bin\mvn.cmd" (
    "C:\Program Files\apache-maven-3.9.12\bin\mvn.cmd" %*
) else (
    mvn %*
)
endlocal
