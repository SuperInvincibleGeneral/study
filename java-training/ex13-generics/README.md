# 課題13: ジェネリクスとワイルドカード（難易度 ★★★★☆）

## この課題のねらい
ジェネリクスのコンパイルエラーは文章が長く、初見では読む気を失う。
**長いエラーメッセージから「本体の1行」を抜き出して検索する** 練習をする。

## 状況
`work/DataUtils.java` は集計ユーティリティ。**テストコードは正しい**が、
現在の `DataUtils` のシグネチャではテストがコンパイルできない。
`DataUtils` 側を直すこと。

出るエラーの例:
```
incompatible types: List<Integer> cannot be converted to List<Number>
```

## 仕様（テストが期待する動き）
| メソッド | 要求 |
|---|---|
| `sum` / `max` | `List<Integer>` `List<Double>` `List<Number>` の **どれでも** 渡せる |
| `copyInts(from, to)` | `to` は `List<Integer>` でも `List<Number>` でも渡せる |
| `first(list)` | `List<String>` を渡したら **キャスト無しで** `String` が返る |
| `newList(items...)` | `newList("x","y")` の戻り値が `List<String>` として使える |

## 手順
1. `work/DataUtils.java` を編集する
2. `./run.sh 13` （Windows は `run.bat 13`）

## 制約
- `test/` と `common/` は編集しない
- メソッド名と処理内容は変えない。**変えるのは型の書き方だけ**
- `@SuppressWarnings` でごまかさない。警告が出ない書き方にすること
- テスト側にキャスト（`(String)` など）を足して解決しようとしないこと（テストは編集禁止）

## 自力で調べるためのポイント
- 最初の疑問はこれ。**「`Integer` は `Number` の一種なのに、なぜ `List<Integer>` を `List<Number>` に渡せないのか」**
  この疑問文をそのまま検索窓に入れてよい。
  - 例: `java List<Integer> List<Number> 渡せない なぜ`
  - 例: `java generics 非変 共変 invariant`
- 解決策には **「PECS」** という3文字の合言葉がある。この単語にたどり着けたら勝ち。
  - 例: `java ジェネリクス ワイルドカード extends super 使い分け`
  - 例: `java PECS 意味`
- `List`（型パラメータ無し）は **raw type（原型）** と呼ばれる。
  コンパイル時に `unchecked` 警告が出るはず。**警告も必ず読むこと。**
  - 例: `java raw type 警告 unchecked なぜ良くない`
  - 例: `java ジェネリックメソッド 書き方 <T>`
- コンパイルするとき `javac -Xlint:all` を付けると、隠れている警告が全部出る。試してみること。

## 検索ログ（提出時に埋めること）
| # | エラー/警告の本体1行 | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |
