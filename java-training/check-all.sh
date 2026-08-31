#!/bin/sh
# 講師用: 全課題の解答例が通ることを確認する。
# （新人の提出物を確認したいときは ./run.sh <番号> を1問ずつ実行すること）
set -u
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
TARGET="${1:-solution}"
ng=0
for dir in "$BASE_DIR"/ex*/; do
  no=$(basename "$dir" | sed 's/^ex\([0-9]*\).*/\1/')
  if "$BASE_DIR/run.sh" "$no" "$TARGET" >/dev/null 2>&1; then
    echo "PASS  $(basename "$dir")"
  else
    echo "FAIL  $(basename "$dir")"
    ng=$((ng + 1))
  fi
done
echo "----"
echo "失敗 $ng 件"
[ "$ng" -eq 0 ]
