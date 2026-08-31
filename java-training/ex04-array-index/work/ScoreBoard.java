/**
 * 課題04: 得点表。
 * コンパイルは通るが、実行すると例外が出たり、答えが合わなかったりする。
 */
public class ScoreBoard {

    private final int[] scores;

    public ScoreBoard(int[] scores) {
        this.scores = scores;
    }

    /** 最高点を返す。得点が1件も無い場合は IllegalStateException を投げる。 */
    public int max() {
        int max = scores[0];
        for (int i = 1; i <= scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    /** 末尾から n 件の合計を返す。n が件数を超える場合は全件の合計を返す。 */
    public int sumOfLast(int n) {
        int sum = 0;
        for (int i = scores.length - n - 1; i < scores.length; i++) {
            sum += scores[i];
        }
        return sum;
    }

    /** index 番目の得点を返す。範囲外なら -1 を返す。 */
    public int at(int index) {
        return scores[index];
    }
}
