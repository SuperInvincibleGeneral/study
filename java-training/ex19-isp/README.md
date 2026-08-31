# 課題19: インターフェース分離の原則 ISP（難易度 ★★★★☆）

## この課題のねらい
`UnsupportedOperationException` を投げる実装が並んでいたら、それは設計の匂い（code smell）。
SOLID の I、インターフェース分離の原則（Interface Segregation Principle）を扱う。

## 状況
`work/MultiFunctionDevice.java` は「オフィス機器は全部これを実装すればいい」と作られた
大きなインターフェース。その結果、印刷しかできない `SimplePrinter` まで
`scan()` / `fax()` の実装を強制され、呼ばれると例外で落ちる。

利用する側から見ると、**型を見ても「どのメソッドを呼んでよいか分からない」** のが致命的。
実行してみるまで壊れているか分からない。

## 求められる構成（テストがこの名前で呼び出す）
| 型 | メンバ |
|---|---|
| `interface Printer` | `String print(String document)` |
| `interface DocumentScanner` | `String scan()` |
| `interface FaxDevice` | `String fax(String document, String number)` |
| `class SimplePrinter` | `Printer` **だけ** を実装 |
| `class MultiFunctionPrinter` | `Printer`, `DocumentScanner`, `FaxDevice` を実装 |
| `class PrintJob` | `PrintJob(Printer printer)` / `List<String> printAll(List<String> documents)` |

戻り値の形式は現在の実装と同じ（`"印刷: 見積書"`, `"スキャン結果"`, `"FAX送信: 注文書 -> 03-0000-0000"`）。

## 手順
1. `work/` を上記の構成に作り替える（`MultiFunctionDevice.java` は削除してよい）
2. `./run.sh 19` （Windows は `run.bat 19`）

## 制約
- `test/` と `common/` は編集しない
- **`UnsupportedOperationException` を1か所も書かないこと**
- **各インターフェースのメソッドは1つだけ**（テストが数を検証する）
- `PrintJob` の引数の型は `Printer`。`MultiFunctionPrinter` を直接受け取らないこと

## 自力で調べるためのポイント
- まず「`UnsupportedOperationException` を投げる実装」がなぜ悪いのかを調べること。
  - 例: `java UnsupportedOperationException 設計 悪い`
  - 例: `インターフェース 実装 空メソッド アンチパターン`
- 原則の名前で調べる。
  - 例: `インターフェース分離の原則 とは 例`
  - 例: `fat interface 太ったインターフェース 問題`
- **「小さいインターフェースをいくつも作ると管理が増えるのでは？」** という疑問は正しい。
  この疑問を持ったまま、賛否の議論を読むこと。片方の意見だけ読んで納得しないのが調査力。
  - 例: `インターフェース 細かく分ける デメリット`
- 実務での判断軸: **「その型を使う側が、何を呼べる必要があるか」** から逆算して設計する。
  「機器の全機能を列挙する」ではなく「利用者が要求する能力を書く」。
  - 例: `role interface とは`

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |

## 振り返り（提出時に記入）
- 分割したことで、`PrintJob` を書く人にとって何が楽になったか:
- 逆に増えた手間は何か:
