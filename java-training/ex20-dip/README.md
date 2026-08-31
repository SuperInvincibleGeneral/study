# 課題20: 依存性逆転の原則 DIP（難易度 ★★★★★）

## この課題のねらい
**「テストできないコード」は、たいてい設計に問題がある。**
SOLID の D、依存性逆転の原則（Dependency Inversion Principle）を扱う。
あわせて「現在時刻も依存である」という、実務で効く視点を身につける。

## 状況
`work/OrderService.java` には2つの問題がある。
1. 具体的な送信手段 `SmtpMailSender` を **自分で `new` している**
   → テストすると本番のメール送信が呼ばれてしまう。差し替える隙間が無い
2. 現在時刻を `LocalDateTime.now()` で **直接取っている**
   → 実行するたび結果が変わるので「注文日が正しく入るか」を検証できない

## 求められる構成（テストがこの名前で呼び出す）
| 型 | メンバ |
|---|---|
| `interface MailGateway` | `void send(String to, String subject, String body)` |
| `class SmtpMailGateway implements MailGateway` | 本番用。中で `SmtpMailSender` を使う |
| `class OrderService` | `OrderService(MailGateway gateway, java.time.Clock clock)` |
| | `Order placeOrder(String customerEmail, String item, int amount)` |

送信されるメール:
- 宛先: 注文者のメールアドレス
- 件名: `ご注文確認`
- 本文:
  ```
  ご注文ありがとうございます。
  商品: Java入門
  金額: 3000円
  注文日: 2026/04/01
  ```
  （注文日は `Clock` から取った日付を `yyyy/MM/dd` で整形）

## 手順
1. `work/` を上記の構成に作り替える
2. `./run.sh 20` （Windows は `run.bat 20`）

## 制約
- `test/` と `common/` は編集しない
- `SmtpMailSender.java` と `Order.java` は変更しない
- **`OrderService` の中で `new` を書かないこと**（`Order` の生成は除く）
- **`OrderService` の中で `LocalDateTime.now()`（引数なし）を呼ばないこと**
- `OrderService` のフィールドの型は抽象（`MailGateway`）であること。テストが検証する

## 自力で調べるためのポイント
- 「テストのときだけ別のものに差し替えたい」という要求に対する定番の解き方を調べること。
  - 例: `java 依存性注入 コンストラクタ 使い方`
  - 例: `java テスト 本番のメール送信 差し替え モック スタブ`
  - 例: `依存性逆転の原則 とは 例`
- **`new` がなぜテストの敵になるのか** を説明できるようにすること。
  - 例: `java new 直接生成 テストしにくい 理由`
- **現在時刻の扱い** は実務で必ずぶつかる。Java には専用の仕組みがある。
  - 例: `java 現在時刻 テスト 固定 Clock`
  - 例: `java Clock.fixed 使い方`
  - `LocalDateTime.now()` に `Clock` を渡せる版があることを Javadoc で確認すること
- **DIP は「インターフェースを挟むこと」ではない。** ここを取り違える人が非常に多い。
  「抽象を、どちら側の都合で定義するか」が本質。賛否や解説記事を複数読み比べること。
  - 例: `依存性逆転の原則 誤解 インターフェース 挟むだけ`
  - 例: `DIP DI 違い`
- テスト用の偽物には種類と呼び名がある（スタブ / モック / フェイク / スパイ）。
  - 例: `テストダブル 種類 違い`

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |

## 振り返り（提出時に記入）
- `MailGateway` を「SMTP の都合」ではなく「`OrderService` の都合」で定義した、とはどういう意味か:
- 現在時刻のほかに「依存」として外から渡したほうがよいものを3つ挙げよ:
