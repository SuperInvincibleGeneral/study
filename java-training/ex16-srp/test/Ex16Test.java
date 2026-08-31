import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Ex16Test {

    public static void main(String[] args) {
        Assert.suite("課題16 単一責任の原則(SRP)", () -> {

            List<Sale> sales = Arrays.asList(
                    new Sale("Books", "Java入門", 3000),
                    new Sale("Food", "弁当", 800),
                    new Sale("Books", "設計入門", 2000),
                    new Sale("Food", "コーヒー", 400));

            // --- 集計役だけを、画面出力なしでテストできること ---
            SalesAggregator aggregator = new SalesAggregator();
            Assert.eq("SalesAggregator#total: 合計", 6200, aggregator.total(sales));
            Assert.eq("SalesAggregator#total: 空なら 0", 0, aggregator.total(new ArrayList<Sale>()));

            Map<String, Integer> byCategory = aggregator.byCategory(sales);
            Assert.eq("SalesAggregator#byCategory: 金額降順で並ぶ",
                    "{Books=5000, Food=1200}", byCategory.toString());
            Assert.eq("SalesAggregator#byCategory: 空なら空マップ",
                    "{}", aggregator.byCategory(new ArrayList<Sale>()).toString());
            Assert.eq("SalesAggregator#topCategory", "Books", aggregator.topCategory(sales));
            Assert.eq("SalesAggregator#topCategory: 空なら空文字", "",
                    aggregator.topCategory(new ArrayList<Sale>()));

            // --- 整形役だけを、集計を通さずテストできること ---
            SalesReportFormatter formatter = new SalesReportFormatter();
            List<String> lines = formatter.format(
                    new java.util.LinkedHashMap<>(Map.of("Books", 5000)), 5000);
            Assert.eq("SalesReportFormatter: 3行返る（出力はしない）", 3, lines.size());
            Assert.eq("SalesReportFormatter: 見出し", "=== 売上レポート ===", lines.get(0));
            Assert.eq("SalesReportFormatter: 明細", "Books : 5000", lines.get(1));
            Assert.eq("SalesReportFormatter: 合計行", "合計 : 5000", lines.get(2));

            // --- 組み立て役: 集計 + 整形。画面には出さず行を返すこと ---
            SalesReporter reporter = new SalesReporter();
            List<String> report = reporter.report(sales);
            Assert.eq("SalesReporter#report: 4行", 4, report.size());
            Assert.eq("SalesReporter#report: 1行目", "=== 売上レポート ===", report.get(0));
            Assert.eq("SalesReporter#report: 2行目", "Books : 5000", report.get(1));
            Assert.eq("SalesReporter#report: 3行目", "Food : 1200", report.get(2));
            Assert.eq("SalesReporter#report: 4行目", "合計 : 6200", report.get(3));

            Assert.eq("SalesReporter#report: 空なら見出しと合計だけ", 2,
                    reporter.report(new ArrayList<Sale>()).size());
        });
    }
}
