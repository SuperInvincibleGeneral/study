# 課題11: equals と hashCode の契約（難易度 ★★★☆☆）

## この課題のねらい
「動くコードは書けるが、標準ライブラリの **前提（契約）** を知らないので落とし穴にはまる」
という段階を抜ける。ここは **公式ドキュメント（Javadoc）を読まないと絶対に解けない** 課題。

## 状況
`work/Book.java` を使うと次の不具合が起きる。
- 同じ ISBN の本を `HashSet` に入れると重複してしまう
- `HashMap` に入れた本が、同じ ISBN の別インスタンスでは取り出せない
- `list.contains(book)` が false になる

**同一性は ISBN だけで決まる。** タイトルや価格が違っても ISBN が同じなら同じ本とみなす。

## 仕様（テストが期待する動き）
- `a.equals(b)` … ISBN が同じなら true
- `null` と比較したら false、違う型と比較したら false
- `a.equals(b)` が true なら `a.hashCode() == b.hashCode()`
- `HashSet` / `HashMap` / `List#contains` で正しく重複判定・検索ができる
- `toString()` は現状のまま変えない

## 手順
1. `work/Book.java` を編集する
2. `./run.sh 11` （Windows は `run.bat 11`）

## 制約
- `test/` と `common/` は編集しない
- **IDE の自動生成（Generate equals() and hashCode()）を使わずに、まず自分で書くこと。**
  書き終わってから IDE の生成結果と見比べ、違いを README の検索ログに書くこと
- `record` への書き換えは禁止（今回は equals/hashCode を手で書くことが目的）

## 自力で調べるためのポイント
- まず **なぜ HashSet が重複を許してしまうのか** を考える。
  `HashSet` は内部で何を使って「同じかどうか」を判断しているか？
  - 例: `java HashSet 重複 判定 仕組み hashCode equals`
  - 例: `java HashMap 内部構造 バケット hashCode`
- 次に **契約（contract）** という言葉で検索する。Javadoc の `Object#equals` / `Object#hashCode`
  には守るべき条件が箇条書きで書いてある。**そこが一次情報。**
  - 例: `java Object equals hashCode 規約 javadoc`
- `equals` の引数の型を `Book` にすると何が起きるか、試してみること。
  そのとき `@Override` を付けるとコンパイラが何と言うか、**実際に出力させて確認すること**。
  - 例: `java equals オーバーロード オーバーライド 違い @Override`
- ハッシュ値を自分で計算する定番の方法も調べておくこと。
  - 例: `java Objects.hash 使い方`

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 | IDE の自動生成との違い | — | — |  |
