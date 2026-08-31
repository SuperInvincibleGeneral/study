# 課題10: 例外の設計と例外連鎖（難易度 ★★★☆☆）

## この課題のねらい
「例外が出たから try-catch で囲む」で止まっている状態から一段上がる。
- **チェック例外 / 非チェック例外の違い**
- **握りつぶし（catch して握り潰す）がなぜ悪いのか**
- **例外を包み直すときに cause を渡す意味**

を、自分で調べて理解する。

## 状況
`work/ConfigReader.java` は設定値（文字列）を読み取るクラス。
`work/ConfigException.java` は **中身が空** なので、まずこれを完成させる必要がある。

## 仕様（テストが期待する動き）

### `ConfigException`
- **非チェック例外** であること（呼び出し側に `try-catch` を強制しない）
- `ConfigException(String message)` と `ConfigException(String message, Throwable cause)` の2つのコンストラクタを持つ

### `ConfigReader`
| メソッド | null / 空白 | 変換できない | 範囲外 |
|---|---|---|---|
| `readPort` | `8080` | `ConfigException`（cause に `NumberFormatException`） | 1〜65535 以外は `ConfigException` |
| `readTimeout` | `30` | `ConfigException` | 0 以下は `ConfigException` |
| `readEnabled` | `false` | — | `true/false/yes/no/1/0` 以外は `ConfigException` |

- 前後の空白は無視して読めること（`"  9090  "` → `9090`）
- 例外メッセージには **元の入力値** を含めること

## 手順
1. `work/ConfigException.java` を完成させる
2. `work/ConfigReader.java` を編集する
3. `./run.sh 10` （Windows は `run.bat 10`）

## 制約
- `test/` と `common/` は編集しない
- **`catch (Exception e) { return 既定値; }` のような握りつぶしは禁止**
- `catch` の中で `e.printStackTrace()` だけして先に進むのも禁止

## 自力で調べるためのポイント
- `Integer.parseInt(null)` は何の例外になるか？ **想像せずに Javadoc で確認する。**
  - 例: `java Integer parseInt null 例外`
- 自作例外は「何を継承するか」で性質が決まる。まずこの分類を調べること。
  - 例: `java チェック例外 非チェック例外 違い`
  - 例: `java 独自例外 作り方 RuntimeException`
  - 「どちらを継承すべきか」の判断基準を、**自分の言葉で1行にまとめてコメントに書くこと**
- スタックトレースの `Caused by:` はどこから出るのか調べること。
  - 例: `java 例外 cause 原因 連鎖 Caused by`
  - 例: `java 例外 ラップ 再スロー ベストプラクティス`
- 「握りつぶし」には英語の呼び名がある。名前で検索すると議論が大量に出てくる。
  - 例: `java exception swallowing なぜ 悪い`

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |
