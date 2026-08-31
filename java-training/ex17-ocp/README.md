# 課題17: 開放閉鎖の原則 OCP（難易度 ★★★★☆）

## この課題のねらい
**「if / else が増え続けるコード」を、増えない形に作り替える。**
SOLID の O、開放閉鎖の原則（Open-Closed Principle）を扱う。

## 状況
`work/DiscountCalculator.java` は会員種別ごとの割引を `if / else` で分岐している。
会員種別が増えるたびにこのクラスを書き換える必要があり、
書き換えるたびに既存の種別を壊すリスクがある。

いま PLATINUM（20%引き）の追加要望が来ており、この先も種別は増える見込み。

## 求められる構成（テストがこの名前で呼び出す）
| 型 | メンバ | 説明 |
|---|---|---|
| `interface DiscountPolicy` | `String memberType()` | 対応する会員種別（`"GOLD"` など） |
| | `long discount(long amount)` | **割引額**（割引後の金額ではない） |
| `class RegularPolicy` | | 割引なし |
| `class SilverPolicy` | | 5%引き |
| `class GoldPolicy` | | 10%引き |
| `class DiscountCalculator` | `void register(DiscountPolicy)` | 割引方針を追加登録する |
| | `long priceFor(String memberType, long amount)` | 割引後の支払金額。未知の種別・`null` は割引なし |

`new DiscountCalculator()` した時点で REGULAR / SILVER / GOLD は使えること。

## 手順
1. `work/` に上記の型を作る
2. `./run.sh 17` （Windows は `run.bat 17`）

## 制約
- `test/` と `common/` は編集しない
- **`DiscountCalculator` の中に `"PLATINUM"` や `"CAMPAIGN"` と書いてはいけない。**
  テストは、既存クラスを一切変更せずに新種別を追加できるかを見ている
- `DiscountCalculator` に会員種別ごとの `if` / `switch` を残さないこと

## 自力で調べるためのポイント
- この形の設計には名前が付いている。名前にたどり着ければ実装例は大量に出てくる。
  - 例: `if else 増える 設計 リファクタリング`
  - 例: `java ストラテジパターン 使いどころ`
  - 例: `開放閉鎖の原則 OCP 例`
- 「分岐で表現しているものを、型の違いで表現し直す」というのが発想の核。
  リファクタリングのカタログにも載っている（**Replace Conditional with Polymorphism**）。
  - 例: `リファクタリング 条件記述 ポリモーフィズム 置き換え`
- **注意して調べてほしいこと**: 何でもインターフェースにすればよいわけではない。
  「種別が今後増えるか」「増えたときに誰が困るか」で判断する。
  - 例: `java enum switch ストラテジ 使い分け`
  - 過剰設計（over-engineering）という言葉も一緒に調べておくこと

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |

## 振り返り（提出時に記入）
- 今回インターフェースにして良かったと思う理由:
- 逆に「enum + switch のままで十分」なのはどんな場合か:
