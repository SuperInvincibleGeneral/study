# 課題05: NullPointerException の潰し方（難易度 ★★☆☆☆）

## この課題のねらい
NPE は「どこで落ちたか」より **「なぜそこが null になり得るのか」** を考えることが本質。
`Map#get` のように **null を返すことが仕様として決まっている API** を、Javadoc で確認する練習をする。

## 状況
`work/UserDirectory.java` は登録済みの名前ならうまく動くが、未登録の名前や `null` を渡すと落ちる。

## 仕様（テストが期待する動き）
| メソッド | 仕様 |
|---|---|
| `domainOf(name)` | `@` より後ろを返す。**未登録・null なら `"unknown"`** |
| `isAdmin(name)` | `admin@example.com` なら true。**未登録・null なら false（例外にしない）** |
| `countWithDomain(domain)` | 該当する利用者数 |
| `displayName(name)` | 前後の空白を落とす。**null なら `"(名無し)"`** |

## 手順
1. `work/UserDirectory.java` を編集する
2. `./run.sh 05` （Windows は `run.bat 05`）

## 制約
- `test/` と `common/` は編集しない
- **`try { ... } catch (NullPointerException e) { ... }` で握りつぶすのは禁止。**
  null になり得る箇所を特定して、事前に分岐させること。

## 自力で調べるためのポイント
- Java 14 以降の NPE メッセージは
  `Cannot invoke "String.substring(int)" because "email" is null` のように
  **どの変数が null だったか** を教えてくれる。まずこの1行を丸ごと読む。
- 「なぜ null になったのか」は **その API の Javadoc** に書いてある。
  `Map get javadoc` で検索し、戻り値の説明に `null` の文字があるか確認すること。
- 検索キーワードの例
  - `java Map get キーが無い場合 null`
  - `java NullPointerException 回避 定数 equals 順番`
  - `java Objects.equals 使い方`
- 「変数.equals("定数")」と「"定数".equals(変数)」の違いは、**知っているかどうかだけ** で差がつく典型例。
  なぜ後者が安全なのか、自分の言葉で説明できるようにすること。

## 検索ログ（提出時に埋めること）
| # | 出た NPE のメッセージ | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
