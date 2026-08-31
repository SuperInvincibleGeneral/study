@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

rem ============================================================
rem  Java 研修課題 テスト実行スクリプト（Windows 用）
rem
rem    run.bat 01           work\ のコード（自分が編集したもの）をテスト
rem    run.bat 01 solution  solution\ のコード（解答例）をテスト
rem
rem  このファイルは「UTF-8 (BOM 付き)」「改行 CRLF」で保存すること。
rem  chcp の行は日本語を含む行より前に置くこと。順序を変えると文字化けする。
rem ============================================================

set "NO=%~1"
set "TARGET=%~2"

if "%NO%"=="" (
  echo 使い方: run.bat ^<課題番号 01-20^> [work^|solution]
  exit /b 2
)
if "%TARGET%"=="" set "TARGET=work"

set "BASE=%~dp0"

rem 課題フォルダを探す。
rem cmd.exe がワイルドカードを展開してくれるのは FOR の中だけなので、
rem 探索は必ず FOR を通して行う。
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

rem コンパイル対象の .java を集める。
rem
rem javac に "common\*.java" のような文字列をそのまま渡してはいけない。
rem cmd.exe はワイルドカードを展開しないため、javac は
rem 「*.java という名前のファイル」を探しに行って失敗する。
rem
rem また、javac のレスポンスファイル（@ファイル名）も使ってはいけない。
rem javac は @ファイルの中身ではバックスラッシュをエスケープ文字として
rem 解釈するため、Windows のパス区切りが消えてしまう。
rem   例) "C:\Users\taro\Assert.java" -> C:Users<TAB>aroAssert.java
rem
rem そこで、対象フォルダへ移動してから FOR で1件ずつ列挙し、
rem フルパス（%%~fF）を並べてコマンドラインで直接渡す。
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

rem テストクラス名（例: Ex01Test）を取得する。
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
