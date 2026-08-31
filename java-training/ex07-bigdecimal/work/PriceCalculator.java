import java.math.BigDecimal;

/**
 * 課題07: 金額計算。
 * 経理から「1円ずれる」「同じ金額なのに一致しないと言われる」と報告が来ている。
 */
public class PriceCalculator {

    /** 金額（文字列）を合計し、小数第2位までの値を返す。 */
    public static BigDecimal sum(String... amounts) {
        double total = 0;
        for (String amount : amounts) {
            total += Double.parseDouble(amount);
        }
        return new BigDecimal(total);
    }

    /**
     * 税込金額を返す。1円未満は切り捨て、小数部を持たない値にすること。
     * 例) amount="1980", taxRate="0.1" -> 2178
     */
    public static BigDecimal taxIncluded(String amount, String taxRate) {
        double value = Double.parseDouble(amount) * (1 + Double.parseDouble(taxRate));
        return new BigDecimal((int) value);
    }

    /** 2つの金額が「同じ金額か」を判定する。"1.10" と "1.1" は同じ金額とみなす。 */
    public static boolean sameAmount(String a, String b) {
        return new BigDecimal(a).equals(new BigDecimal(b));
    }

    /** amount を people 人で等分した1人あたりの金額（小数第2位、四捨五入）。 */
    public static BigDecimal perPerson(String amount, int people) {
        return new BigDecimal(amount).divide(new BigDecimal(people));
    }
}
