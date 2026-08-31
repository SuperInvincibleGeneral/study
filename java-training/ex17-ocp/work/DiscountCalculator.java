/**
 * 課題17: 会員種別ごとの割引計算。
 *
 * 会員種別が増えるたびに、この if 文を書き換えなければならない。
 * すでに PLATINUM を追加してほしいという要望が来ており、
 * この先も BLACK, STUDENT ... と増える見込み。
 *
 * 「新しい会員種別を追加するときに、このクラスを一切変更しなくてよい」形に作り替えること。
 */
public class DiscountCalculator {

    public long priceFor(String memberType, long amount) {
        if ("REGULAR".equals(memberType)) {
            return amount;
        } else if ("SILVER".equals(memberType)) {
            return amount - (amount * 5 / 100);
        } else if ("GOLD".equals(memberType)) {
            return amount - (amount * 10 / 100);
        } else {
            return amount;
        }
    }
}
