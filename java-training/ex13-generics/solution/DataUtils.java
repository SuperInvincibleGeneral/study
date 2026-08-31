import java.util.ArrayList;
import java.util.List;

/**
 * 課題13 解答例。
 *
 * ポイント（PECS: Producer Extends, Consumer Super）:
 *  1. List<Integer> は List<Number> のサブタイプ「ではない」。
 *     ジェネリクスは既定で非変（invariant）なので、そのままでは渡せない。
 *     -> 読み取るだけ（要素を produce してもらう）なら List<? extends Number> にする。
 *  2. 要素を追加する側（consume する）なら List<? super Integer> にする。
 *     こうすると List<Integer> でも List<Number> でも List<Object> でも受け取れる。
 *  3. 型パラメータを書かない「原型（raw type）」List は、
 *     コンパイル時の型チェックが効かなくなり、実行時に ClassCastException を招く。
 *     -> 型パラメータ <T> を宣言して、戻り値の型まで保つ。
 *  4. <T> は「型引数」。メソッドに付ける場合は戻り値型の前に書く。
 */
public class DataUtils {

    /** 数値のリストの合計を返す。 */
    public static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    /** 数値のリストの最大値を返す。空なら 0。 */
    public static double max(List<? extends Number> numbers) {
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
    public static void copyInts(List<? extends Integer> from, List<? super Integer> to) {
        for (Integer i : from) {
            to.add(i);
        }
    }

    /** リストの先頭要素を返す。空なら null。 */
    public static <T> T first(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /** 与えられた要素から新しいリストを作る。 */
    @SafeVarargs
    public static <T> List<T> newList(T... items) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            result.add(item);
        }
        return result;
    }
}
