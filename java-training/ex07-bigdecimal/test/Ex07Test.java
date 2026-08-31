import java.math.BigDecimal;

public class Ex07Test {

    public static void main(String[] args) {
        Assert.suite("課題07 浮動小数点と BigDecimal", () -> {

            // まずは事実確認: double では 0.1 + 0.2 が 0.3 にならない
            System.out.println("  (参考) 0.1 + 0.2 = " + (0.1 + 0.2));

            Assert.eq("sum: 0.1 + 0.2 は 0.30", new BigDecimal("0.30"), PriceCalculator.sum("0.1", "0.2"));
            Assert.eq("sum: 整数の合計も小数第2位で返す", new BigDecimal("360.00"),
                    PriceCalculator.sum("120", "120", "120"));
            Assert.eq("sum: 引数なしは 0.00", new BigDecimal("0.00"), PriceCalculator.sum());
            Assert.eq("sum: 小数が混ざっても誤差が出ない", new BigDecimal("1.11"),
                    PriceCalculator.sum("0.01", "0.10", "1.00"));

            Assert.eq("taxIncluded: 1980円の税込(10%)は 2178", new BigDecimal("2178"),
                    PriceCalculator.taxIncluded("1980", "0.1"));
            Assert.eq("taxIncluded: 1円未満は切り捨て (105 * 1.08 = 113.4 -> 113)", new BigDecimal("113"),
                    PriceCalculator.taxIncluded("105", "0.08"));
            Assert.eq("taxIncluded: 1000円の税込(29%)は 1290 ★ここが本番", new BigDecimal("1290"),
                    PriceCalculator.taxIncluded("1000", "0.29"));
            Assert.eq("taxIncluded: 3333円の税込(10%)は 3666", new BigDecimal("3666"),
                    PriceCalculator.taxIncluded("3333", "0.1"));

            Assert.isTrue("sameAmount: 1.10 と 1.1 は同じ金額 ★ここが本番",
                    PriceCalculator.sameAmount("1.10", "1.1"));
            Assert.isTrue("sameAmount: 100 と 100.00 は同じ金額",
                    PriceCalculator.sameAmount("100", "100.00"));
            Assert.isFalse("sameAmount: 1.10 と 1.11 は違う金額",
                    PriceCalculator.sameAmount("1.10", "1.11"));

            Assert.eq("perPerson: 1000円を3人で割ると 333.33", new BigDecimal("333.33"),
                    PriceCalculator.perPerson("1000", 3));
            Assert.eq("perPerson: 割り切れる場合も小数第2位", new BigDecimal("500.00"),
                    PriceCalculator.perPerson("1000", 2));
            Assert.eq("perPerson: 四捨五入される (10 / 3 = 3.333... -> 3.33)", new BigDecimal("3.33"),
                    PriceCalculator.perPerson("10", 3));
        });
    }
}
