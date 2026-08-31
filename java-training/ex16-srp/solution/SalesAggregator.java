import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 課題16 解答例: 「集計する」責務だけを持つクラス。
 *
 * 単一責任の原則（SRP）は「1クラス1メソッド」という意味ではない。
 * 「そのクラスを変更する理由が1つだけであること」を指す。
 * 集計ロジックが変わったときに変更されるのはこのクラスだけ、という状態を作る。
 *
 * このクラスは画面出力も文字列整形も一切しない。だから
 *  - 出力先が変わっても影響を受けない
 *  - 単体で（画面を見ずに）テストできる
 */
public class SalesAggregator {

    /** 全体の合計金額。 */
    public int total(List<Sale> sales) {
        int total = 0;
        for (Sale sale : sales) {
            total += sale.getAmount();
        }
        return total;
    }

    /** カテゴリごとの合計金額。金額の降順、同額ならカテゴリ名の昇順で並べて返す。 */
    public Map<String, Integer> byCategory(List<Sale> sales) {
        Map<String, Integer> sums = new TreeMap<>();
        for (Sale sale : sales) {
            sums.merge(sale.getCategory(), sale.getAmount(), Integer::sum);
        }

        Map<String, Integer> sorted = new LinkedHashMap<>();
        sums.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed()
                        .thenComparing(Map.Entry::getKey))
                .forEach(e -> sorted.put(e.getKey(), e.getValue()));
        return sorted;
    }

    /** 最も売れたカテゴリ。売上が無ければ空文字。 */
    public String topCategory(List<Sale> sales) {
        Map<String, Integer> byCategory = byCategory(sales);
        for (String category : byCategory.keySet()) {
            return category;
        }
        return "";
    }
}
