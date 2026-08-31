/** シルバー会員: 5%割引。 */
public class SilverPolicy implements DiscountPolicy {

    @Override
    public String memberType() {
        return "SILVER";
    }

    @Override
    public long discount(long amount) {
        return amount * 5 / 100;
    }
}
