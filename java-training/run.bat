@echo off
chcp 65001 >nul
rem ↑ このファイルは日本語コメントを含むため、chcp は @echo off の直後、
rem   日本語を含む行より前に置くこと。そうしないと Windows が既定のコード
rem   ページ（Shift_JIS 系）で日本語行を読み込み、文字化けだけでなく
rem   直後の setlocal 行の解釈まで壊れる（"setlocal は、内部コマンドまたは
rem   外部コマンド...として認識されていません。" のエラーになる）。
setlocal enabledelayedexpansion

rem 使い方:
rem   run.bat 01           自分が編集した work\ のコードをテストする
rem   run.bat 01 solution  解答例 solution\ のコードをテストする

set NO=%~1
set TARGET=%~2
if "%NO%"=="" (
  echo 使い方: run.bat ^<課題番号 01-20^> [work^|solution]
  exit /b 2
)
if "%TARGET%"=="" set TARGET=work

set BASE=%~dp0
set EXDIR=
for /d %%D in ("%BASE%ex%NO%-*") do set EXDIR=%%D
if "%EXDIR%"=="" (
  echo 課題 %NO% が見つかりません。
  exit /b 2
)

set OUT=%EXDIR%\out-%TARGET%
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

echo == コンパイル (%NO% / %TARGET%) ==
javac -encoding UTF-8 -d "%OUT%" "%BASE%common\*.java" "%EXDIR%\%TARGET%\*.java" "%EXDIR%\test\*.java"
if errorlevel 1 (
  echo.
  echo !! コンパイルに失敗しました。上のエラーメッセージが最初の手がかりです。
  exit /b 1
)

set MAIN=
for %%F in ("%EXDIR%\test\*Test.java") do set MAIN=%%~nF

echo == テスト実行 ==
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "%OUT%" %MAIN%
