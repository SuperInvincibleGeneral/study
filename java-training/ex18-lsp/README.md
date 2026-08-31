# 課題18: リスコフの置換原則 LSP（難易度 ★★★★★）

## この課題のねらい
**「日常語の分類」で継承を決めると壊れる** ことを体験する。
SOLID の L、リスコフの置換原則（Liskov Substitution Principle）。

## 状況
「正方形は長方形の一種だから」と `Square extends Rectangle` にしたところ、
`Rectangle` 型として扱ったときに計算が壊れるようになった。

```java
Rectangle r = new Square(5);
r.setWidth(10);
r.setHeight(4);
r.area();   // 40 のはずが 16 になる
```

`Rectangle` を受け取る既存コードは「幅と高さは独立に変えられる」と信じて書かれている。
`Square` はその前提を破っている。

## 求められる構成（テストがこの名前で呼び出す）
| 型 | メンバ | 説明 |
|---|---|---|
| `interface Shape` | `long area()` | 面積 |
| | `Shape scaled(int factor)` | 各辺を factor 倍した **新しい** 図形。自分自身は変更しない |
| `class Rectangle implements Shape` | `Rectangle(long w, long h)` | |
| | `Rectangle withWidth(long)` / `withHeight(long)` | その辺だけ差し替えた **新しい** 長方形 |
| `class Square implements Shape` | `Square(long side)` | **`Rectangle` を継承しないこと** |

契約: **どの `Shape` も、`scaled(n)` した面積は元の面積の n² 倍になる。**

## 手順
1. `work/` を上記の構成に作り替える
2. `./run.sh 18` （Windows は `run.bat 18`）

## 制約
- `test/` と `common/` は編集しない
- **`Square` は `Rectangle` を継承しないこと**（テストが検証する）
- **`Shape` に `setXxx` を作らないこと**（テストが検証する）
- `scaled` / `withWidth` / `withHeight` は元のインスタンスを変更しないこと

## 自力で調べるためのポイント
- まず、この問題自体が非常に有名な題材で、専用の呼び名がある。
  - 例: `正方形 長方形 継承 問題`
  - 例: `リスコフの置換原則 とは 例`
  - 例: `circle ellipse problem 継承`
- 「継承していいかどうか」の判断基準を調べること。
  - 例: `java 継承 使うべきか 判断基準 is-a`
  - 例: `継承より委譲 なぜ`
- 今回の解き方の鍵は **不変（immutable）にすること**。
  状態を書き換えられなければ、途中で契約を破られることもない。
  - 例: `java イミュータブル 不変オブジェクト メリット`
  - 例: `java with メソッド 不変 更新`
- **契約テスト（contract test）** という考え方も一緒に調べておくとよい。
  「基底型を実装するすべてのクラスに対して、同じテストを流す」というやり方で、
  今回のテストコード（`for (Shape shape : shapes)` の部分）がまさにそれ。
  - 例: `契約テスト インターフェース 共通テスト`

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |

## 振り返り（提出時に記入）
- 「A は B の一種である」と言えるのに継承してはいけないのはなぜか、自分の言葉で:
- 今の職場のコードで、似た危うい継承に心当たりはあるか:
