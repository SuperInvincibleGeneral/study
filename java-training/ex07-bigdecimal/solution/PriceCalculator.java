import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 課題07 解答例。
 *
 * ポイント:
 *  1. double / float は2進数の浮動小数点なので 0.1 を正確に表せない。
 *     金額のような「正確さが要る値」に double を使ってはいけない。
 *  2. BigDecimal を作るときに new BigDecimal(double) を使うと、
 *     double が持っている誤差までそのまま取り込んでしまう。
 *     文字列から作る new BigDecimal(String) か BigDecimal.valueOf を使う。
 *  3. BigDecimal#equals は「値」だけでなく「スケール（小数桁数）」も比べる。
 *     1.10 と 1.1 は equals では false。金額として等しいか見るなら compareTo(...) == 0。
 *  4. divide は割り切れないと ArithmeticException になる。
 *     必ず桁数と丸めモード（RoundingMode）を指定する。
 *  5. 「切り捨て」は RoundingMode.DOWN(FLOOR)、「四捨五入」は RoundingMode.HALF_UP。
 */
public class PriceCalculator {

    /** 金額（文字列）を合計し、小数第2位までの値を返す。 */
    public static BigDecimal sum(String... amounts) {
        BigDecimal total = BigDecimal.ZERO;
        for (String amount : amounts) {
            total = total.add(new BigDecimal(amount));
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 税込金額を返す。1円未満は切り捨て、小数部を持たない値にすること。
     * 例) amount="1980", taxRate="0.1" -> 2178
     */
    public static BigDecimal taxIncluded(String amount, String taxRate) {
        BigDecimal rate = BigDecimal.ONE.add(new BigDecimal(taxRate));
        return new BigDecimal(amount).multiply(rate).setScale(0, RoundingMode.DOWN);
    }

    /** 2つの金額が「同じ金額か」を判定する。"1.10" と "1.1" は同じ金額とみなす。 */
    public static boolean sameAmount(String a, String b) {
        return new BigDecimal(a).compareTo(new BigDecimal(b)) == 0;
    }

    /** amount を people 人で等分した1人あたりの金額（小数第2位、四捨五入）。 */
    public static BigDecimal perPerson(String amount, int people) {
        return new BigDecimal(amount).divide(new BigDecimal(people), 2, RoundingMode.HALF_UP);
    }
}
