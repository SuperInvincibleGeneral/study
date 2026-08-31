#!/bin/sh
# 使い方:
#   ./run.sh 01              … 自分が編集した work/ のコードをテストする
#   ./run.sh 01 solution     … 解答例 solution/ のコードをテストする
#
# 例) ./run.sh 05
set -u

NO="${1:-}"
TARGET="${2:-work}"

if [ -z "$NO" ]; then
  echo "使い方: ./run.sh <課題番号 01-20> [work|solution]"
  exit 2
fi

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
EX_DIR="$(ls -d "$BASE_DIR"/ex"$NO"-* 2>/dev/null | head -1)"

if [ -z "$EX_DIR" ]; then
  echo "課題 $NO が見つかりません。"
  exit 2
fi

OUT="$EX_DIR/out-$TARGET"
rm -rf "$OUT"
mkdir -p "$OUT"

echo "== コンパイル ($(basename "$EX_DIR") / $TARGET) =="
# shellcheck disable=SC2086
javac -encoding UTF-8 -d "$OUT" "$BASE_DIR"/common/*.java "$EX_DIR/$TARGET"/*.java "$EX_DIR"/test/*.java || {
  echo ""
  echo "!! コンパイルに失敗しました。上のエラーメッセージが最初の手がかりです。"
  echo "!! 「エラーの1行目」と「ファイル名:行番号」を必ず読んでから検索してください。"
  exit 1
}

MAIN="$(ls "$EX_DIR"/test/*Test.java | head -1 | xargs basename | sed 's/\.java$//')"
echo "== テスト実行 =="
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -cp "$OUT" "$MAIN"
