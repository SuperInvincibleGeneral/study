import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 課題16 解答例: 「見せ方（整形）」の責務だけを持つクラス。
 *
 * 集計方法が変わってもこのクラスは変わらない。
 * レイアウトが変わったときに変更されるのはこのクラスだけ。
 * 画面にも出さない（出力先を知らない）ので、文字列として検証できる。
 */
public class SalesReportFormatter {

    /** レポートの各行を返す。改行や出力はしない。 */
    public List<String> format(Map<String, Integer> byCategory, int total) {
        List<String> lines = new ArrayList<>();
        lines.add("=== 売上レポート ===");
        for (Map.Entry<String, Integer> entry : byCategory.entrySet()) {
            lines.add(entry.getKey() + " : " + entry.getValue());
        }
        lines.add("合計 : " + total);
        return lines;
    }
}
