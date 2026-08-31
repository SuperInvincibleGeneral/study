/** ゴールド会員: 10%割引。 */
public class GoldPolicy implements DiscountPolicy {

    @Override
    public String memberType() {
        return "GOLD";
    }

    @Override
    public long discount(long amount) {
        return amount * 10 / 100;
    }
}
