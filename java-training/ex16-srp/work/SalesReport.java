import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 課題16: 売上レポート。
 *
 * 1つのクラスが「集計する」「文字列に整形する」「画面に出力する」を全部やっている。
 * このままだと
 *   - 集計結果だけを別画面で使いたい
 *   - 出力先をファイルやメールに変えたい
 *   - 整形だけをテストしたい
 * のどれも出来ない。テストコードが要求するクラス構成に分割すること。
 */
public class SalesReport {

    public void printReport(List<Sale> sales) {
        Map<String, Integer> byCategory = new HashMap<>();
        int total = 0;
        for (Sale sale : sales) {
            total += sale.getAmount();
            Integer current = byCategory.get(sale.getCategory());
            byCategory.put(sale.getCategory(), (current == null ? 0 : current) + sale.getAmount());
        }

        List<String> categories = new ArrayList<>(byCategory.keySet());
        System.out.println("=== 売上レポート ===");
        for (String category : categories) {
            System.out.println(category + " : " + byCategory.get(category));
        }
        System.out.println("合計 : " + total);
    }
}
