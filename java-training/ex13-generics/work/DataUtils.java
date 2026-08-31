import java.util.ArrayList;
import java.util.List;

/**
 * 課題13: 集計ユーティリティ。
 * テストコードから呼ぶとコンパイルエラーになる。テストは正しい。呼ばれる側を直すこと。
 */
public class DataUtils {

    /** 数値のリストの合計を返す。 */
    public static double sum(List<Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    /** 数値のリストの最大値を返す。空なら 0。 */
    public static double max(List<Number> numbers) {
        double max = 0;
        boolean first = true;
        for (Number n : numbers) {
            if (first || n.doubleValue() > max) {
                max = n.doubleValue();
                first = false;
            }
        }
        return max;
    }

    /** from の要素をすべて to に追加する。 */
    public static void copyInts(List<Integer> from, List<Integer> to) {
        for (Integer i : from) {
            to.add(i);
        }
    }

    /** リストの先頭要素を返す。空なら null。 */
    public static Object first(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /** 与えられた要素から新しいリストを作る。 */
    public static List newList(Object... items) {
        List result = new ArrayList();
        for (Object item : items) {
            result.add(item);
        }
        return result;
    }
}
