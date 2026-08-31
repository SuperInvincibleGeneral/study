# 課題14: リソースの後始末と例外の扱い（難易度 ★★★★☆）

## この課題のねらい
- **例外が出ないのにデータが消える** タイプの不具合（close 漏れ・上書き）を扱う
- チェック例外の正しい扱い方（握りつぶさない）
- 「自分で書く前に、標準ライブラリに用意が無いか探す」姿勢を身につける

## 状況
`work/LogFile.java` には次の不具合がある。
- `write()` したのに **ファイルが空**
- `append()` すると **前の内容が消える**
- 存在しないファイルを読むと、例外も出ずに `null` が返ってきて、呼び出し側が NPE で落ちる
- `countErrors()` が、メッセージ中に "ERROR" の文字を含む INFO 行まで数えてしまう

ログの1行は `レベル<TAB>メッセージ` の形式。

## 仕様（テストが期待する動き）
| メソッド | 仕様 |
|---|---|
| `write(path, lines)` | 全行を書き出す（既存内容は消す）。**書いた内容が確実にファイルに残ること** |
| `append(path, line)` | 1行追記。**既存の内容を消さないこと** |
| `readLines(path)` | 全行を読む。**ファイルが無ければ `UncheckedIOException`（cause は `NoSuchFileException`）** |
| `countErrors(path)` | **レベルが `ERROR` の行だけ** 数える |

文字コードは **UTF-8 固定**（環境依存にしない）。

## 手順
1. `work/LogFile.java` を編集する
2. `./run.sh 14` （Windows は `run.bat 14`）

## 制約
- `test/` と `common/` は編集しない
- **`catch (IOException e) { return null; }` や `e.printStackTrace()` だけで先に進むのは禁止**
- ファイルを開いたら **必ず閉じること**。`finally` に自分で `close()` を書く方法と、
  `try-with-resources` を使う方法の両方を試し、どちらを選んだか理由をコメントに書くこと

## 自力で調べるためのポイント
- **「書いたのにファイルが空」** はファイル操作の超定番。名前で調べられる。
  - 例: `java FileWriter 書き込まれない close flush`
  - 例: `java 出力 バッファ flush とは`
- **「追記したいのに上書きされる」** も定番。API の引数を Javadoc で確認すること。
  - 例: `java FileWriter 追記 append`
- **確実に閉じる書き方** には名前が付いている。Java 7 から入った構文。
  - 例: `java try-with-resources 使い方`
  - 例: `java finally close 例外 リソースリーク`
- **チェック例外をどう扱うか**。「呼び出し側に強制したくないが握りつぶしたくもない」ときの定石。
  - 例: `java IOException UncheckedIOException 包む`
- **そもそも短く書けないか。** `java.nio.file.Files` に、行の読み書きを1行で済ませるメソッドがある。
  Javadoc のメソッド一覧を上から眺めて探すこと。
  - 例: `java Files readAllLines write javadoc`
  - **「自分で while ループを書く前に、標準に無いか探す」** が今回いちばん持ち帰ってほしい癖。

## 検索ログ（提出時に埋めること）
| # | 症状 | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |
