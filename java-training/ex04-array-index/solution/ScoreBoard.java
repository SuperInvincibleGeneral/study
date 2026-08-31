/**
 * 課題04 解答例。
 *
 * ポイント:
 *  1. for の継続条件 i <= length は必ず範囲外を触る。配列の添字は 0 〜 length-1。
 *     -> ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
 *     Java 9 以降は例外メッセージに「何番目を触ったか」「長さはいくつか」が入るので、
 *     まずそのメッセージを読むだけで原因の半分は分かる。
 *  2. 末尾 n 件の開始位置は length - n。 -1 は付けない（off-by-one エラー）。
 *  3. 「範囲外なら -1」のような仕様は、例外に頼らず自分で境界チェックを書く（ガード節）。
 *  4. 空配列で scores[0] を触る前に、件数を確認する。
 */
public class ScoreBoard {

    private final int[] scores;

    public ScoreBoard(int[] scores) {
        this.scores = scores;
    }

    /** 最高点を返す。得点が1件も無い場合は IllegalStateException を投げる。 */
    public int max() {
        if (scores.length == 0) {
            throw new IllegalStateException("得点が1件もありません");
        }
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    /** 末尾から n 件の合計を返す。n が件数を超える場合は全件の合計を返す。 */
    public int sumOfLast(int n) {
        int start = Math.max(0, scores.length - n);
        int sum = 0;
        for (int i = start; i < scores.length; i++) {
            sum += scores[i];
        }
        return sum;
    }

    /** index 番目の得点を返す。範囲外なら -1 を返す。 */
    public int at(int index) {
        if (index < 0 || index >= scores.length) {
            return -1;
        }
        return scores[index];
    }
}
