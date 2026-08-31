/**
 * 課題17 解答例: 割引の「拡張ポイント」。
 *
 * 開放閉鎖の原則（OCP）: 「拡張に対して開いていて、修正に対して閉じている」。
 * 新しい会員種別が増えても、この interface を実装したクラスを1つ足すだけでよく、
 * 既存のクラス（DiscountCalculator や既存のポリシー）には一切手を入れない。
 */
public interface DiscountPolicy {

    /** この方針が対応する会員種別（"GOLD" など）。 */
    String memberType();

    /** 割引額を返す（割引後の金額ではない）。 */
    long discount(long amount);
}
