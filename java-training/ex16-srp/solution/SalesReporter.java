import java.util.List;
import java.util.Map;

/**
 * 課題16 解答例: 集計役と整形役を「組み立てる」だけの調整役。
 *
 * 自分では計算も整形もしない。手順を並べるだけなので変更理由が少ない。
 * 画面出力は呼び出し側（main など）の責務にした。
 * その結果、このクラスも System.out に依存せずテストできる。
 */
public class SalesReporter {

    private final SalesAggregator aggregator = new SalesAggregator();
    private final SalesReportFormatter formatter = new SalesReportFormatter();

    public List<String> report(List<Sale> sales) {
        Map<String, Integer> byCategory = aggregator.byCategory(sales);
        int total = aggregator.total(sales);
        return formatter.format(byCategory, total);
    }

    /** 実際に画面へ出したいときは、呼び出し側でこうする（出力はここだけの責務）。 */
    public static void main(String[] args) {
        List<Sale> sales = List.of(
                new Sale("Books", "Java入門", 3000),
                new Sale("Food", "弁当", 1200));
        new SalesReporter().report(sales).forEach(System.out::println);
    }
}
