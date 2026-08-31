# 課題15: 継承・オーバーライド・フィールドの隠蔽（難易度 ★★★★☆）

## この課題のねらい
「オーバーライドしたつもり」で起きる事故を3種類まとめて体験する。
特に **フィールドは多態にならない** という事実は、知らないと一生ハマる。

## 状況
`work/` には3つのファイルがある。まずコンパイルが通らない。
通したあとも、`summary()` の件名が空になる不具合が残る。

```
EmailNotification is not abstract and does not override abstract method render()
method does not override or implement a method from a supertype
```

## 仕様（テストが期待する動き）
| 呼び出し | 期待する結果 |
|---|---|
| `new EmailNotification("請求書","a@example.com","ご確認ください").render()` | `ご確認ください [Email]` |
| 同上 `.summary()` | `[請求書] to a@example.com : ご確認ください [Email]` |
| `new SmsNotification("再配達","090-0000-0000").render()` | `再配達 [SMS]` |
| 同上 `.summary()` | `[再配達] to 090-0000-0000 : 再配達 [SMS]` |
| `List<Notification>` に入れてループしても、それぞれの実装が呼ばれること | — |

## 手順
1. `work/EmailNotification.java` と `work/SmsNotification.java` を編集する
2. `./run.sh 15` （Windows は `run.bat 15`）

## 制約
- `test/` と `common/` は編集しない
- **`Notification.java`（基底クラス）は変更しないこと。** 直すのはサブクラス側
- **オーバーライドするメソッドには必ず `@Override` を付けること**
- `instanceof` で型を判定して分岐する書き方は禁止（多態で解くこと）

## 自力で調べるためのポイント
- 1つめのエラー: `is not abstract and does not override abstract method`
  「実装したはずなのに、実装したと認識されていない」。**何が1文字違うか** を目で追うこと。
  - 例: `java is not abstract and does not override abstract method 原因`
  - 例: `java @Override 付ける意味 メリット`
- 2つめのエラー: `method does not override or implement a method from a supertype`
  引数が違うと別メソッド扱いになる。この2つの違いを言葉で説明できるようにすること。
  - 例: `java オーバーライド オーバーロード 違い`
- 3つめ（コンパイルは通るバグ）: 件名が空になる。
  親クラスと子クラスに **同じ名前のフィールド** があるとどうなるか、を調べる。
  この現象には名前が付いている。
  - 例: `java 親クラス 子クラス 同じ名前 フィールド`
  - 例: `java field hiding フィールドの隠蔽`
  - 例: `java フィールド 多態 されない`
  - **確認方法**: `System.out.println(this.title + " / " + super.title);` を子クラスに書いて実行し、
    値が違うことを自分の目で見ること。**検索の前に観察。**
- 発展: なぜ「メソッドは多態になるのにフィールドはならないのか」も調べておくと、
  実務で似た事故を避けられる。

## 検索ログ（提出時に埋めること）
| # | エラー/症状 | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |
