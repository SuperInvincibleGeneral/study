@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

rem ============================================================
rem  Java training - test runner for Windows
rem
rem    run.bat 01           test the code in work
rem    run.bat 01 solution  test the sample answer in solution
rem
rem  Save this file as UTF-8 with BOM, using CRLF line endings.
rem  Keep the chcp line above every line that is not plain ASCII.
rem
rem  Keep every rem comment in plain ASCII, and never put the
rem  redirection characters in a rem line. cmd.exe still treats
rem  them as redirection even inside a comment, which silently
rem  breaks the script. Japanese notes belong in README.md.
rem ============================================================

set "NO=%~1"
set "TARGET=%~2"

if "%NO%"=="" (
  echo 使い方: run.bat ^<課題番号 01-20^> [work^|solution]
  exit /b 2
)
if "%TARGET%"=="" set "TARGET=work"

set "BASE=%~dp0"

rem Find the exercise folder.
rem cmd.exe expands wildcards only inside FOR, so always search
rem through a FOR loop.
set "EXDIR="
for /d %%D in ("%BASE%ex%NO%-*") do set "EXDIR=%%D"

if not defined EXDIR (
  echo 課題 %NO% が見つかりません。
  exit /b 2
)
if not exist "%EXDIR%\%TARGET%\" (
  echo フォルダがありません: %EXDIR%\%TARGET%
  exit /b 2
)
if not exist "%EXDIR%\test\" (
  echo フォルダがありません: %EXDIR%\test
  exit /b 2
)
if not exist "%BASE%common\" (
  echo フォルダがありません: %BASE%common
  echo java-training フォルダ一式が揃った状態で実行してください。
  exit /b 2
)

set "OUT=%EXDIR%\out-%TARGET%"
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

rem Collect the .java files to compile.
rem
rem Never pass a pattern such as common\*.java to javac. cmd.exe
rem does not expand wildcards for ordinary programs, so javac
rem would look for a file literally named *.java and fail.
rem
rem Never use a javac response file (@file) either. javac treats a
rem backslash inside a response file as an escape character, so
rem Windows path separators are eaten. For example the path
rem C:\Users\taro\Assert.java becomes C:UsersaroAssert.java, and
rem the \t even turns into a tab character.
rem
rem So: enter each folder, list the files with FOR, and pass the
rem full paths (%%~fF) directly on the command line.
set "SRC="

pushd "%BASE%common"
for %%F in (*.java) do set SRC=!SRC! "%%~fF"
popd

pushd "%EXDIR%\%TARGET%"
for %%F in (*.java) do set SRC=!SRC! "%%~fF"
popd

pushd "%EXDIR%\test"
for %%F in (*.java) do set SRC=!SRC! "%%~fF"
popd

if not defined SRC (
  echo コンパイル対象の .java がありません。
  exit /b 2
)

echo == コンパイル (%NO% / %TARGET%) ==
javac -encoding UTF-8 -d "%OUT%" !SRC!
if errorlevel 1 (
  echo.
  echo [エラー] コンパイルに失敗しました。
  echo          上のエラーメッセージが最初の手がかりです。
  echo          「ファイル名:行番号: error:」の1件目から読んでください。
  exit /b 1
)

rem Get the test class name, for example Ex01Test.
set "MAIN="
pushd "%EXDIR%\test"
for %%F in (*Test.java) do set "MAIN=%%~nF"
popd

if not defined MAIN (
  echo テストクラスが見つかりません: %EXDIR%\test
  exit /b 2
)

echo == テスト実行 ==
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "%OUT%" %MAIN%
exit /b %errorlevel%
