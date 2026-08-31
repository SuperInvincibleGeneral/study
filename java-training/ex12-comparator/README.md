# 課題12: 並べ替え（Comparable / Comparator）（難易度 ★★★☆☆）

## この課題のねらい
- **「引き算で大小比較」というよくある誤りが、なぜ本番だけで壊れるか** を理解する
- 「基本の並び順」と「場面ごとの並び順」を Java がどう分けているかを調べる
- 「メソッドがリストを壊す（破壊的）かどうか」を Javadoc で確認する習慣をつける

## 状況
`work/Employee.java` と `work/EmployeeSorter.java` に次の問題がある。
- 給与が非常に大きい社員（特別報酬 30億）が、なぜか一番安い人として並ぶ
- 並べ替えを呼ぶと **呼び出し元のリストまで並び順が変わってしまう**
- 複数キーの並べ替えメソッドは中身が空（`return null;`）で未実装

## 仕様（テストが期待する動き）
| メソッド | 並び順 |
|---|---|
| `Employee#compareTo` | 給与の昇順 |
| `bySalaryAsc` | 給与の昇順 |
| `bySalaryDescThenName` | 給与の降順 → 同額なら名前の昇順 |
| `byDepartmentThenSalaryDesc` | 部署名の昇順 → 同部署なら給与の降順 |

**すべてのメソッドは、引数で渡されたリストを変更してはいけない**（新しいリストを返す）。

## 手順
1. `work/Employee.java` と `work/EmployeeSorter.java` を編集する
2. `./run.sh 12` （Windows は `run.bat 12`）

## 制約
- `test/` と `common/` は編集しない
- 並べ替えは自分でバブルソートなどを書かず、**標準のソートを使うこと**
- `bySalaryDescThenName` と `byDepartmentThenSalaryDesc` は **`Comparator` を使って書くこと**

## 自力で調べるためのポイント
- **なぜ 30億の人が最下位になるのか。** ここは「勘」ではなく数値で確かめる。
  `System.out.println((int)(4_000_000L - 3_000_000_000L));` を実行して、目で見ること。
  - 例: `java int オーバーフロー 桁あふれ`
  - 例: `java compareTo 引き算 ダメ 理由`
  - 標準に「安全に大小を比べる」ためのメソッドがある。`Long` / `Integer` の Javadoc を見ること
- **`Comparable` と `Comparator` の違い** は必ず調べること。「どちらをいつ使うか」が要点。
  - 例: `java Comparable Comparator 違い 使い分け`
- **複数キーの並べ替え** は Java 8 以降とても短く書ける。
  - 例: `java Comparator 複数条件 ソート thenComparing`
  - 例: `java Comparator 降順 reversed reverseOrder 違い`
  - `reversed()` と `Comparator.reverseOrder()` を取り違えると、
    「部署も逆順になる」という微妙なバグになる。**両方試して違いを確認すること**
- **リストを壊すかどうか**: `Collections.sort` の Javadoc を読み、
  「引数のリストが変更される」と書いてあるかを確認する。
  - 例: `java Collections.sort 破壊的 元のリスト`

## 検索ログ（提出時に埋めること）
| # | 調べたこと | 自分が入れた検索キーワード | 参考にしたページ | 分かったこと |
|---|---|---|---|---|
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |
