/** 一般会員: 割引なし。 */
public class RegularPolicy implements DiscountPolicy {

    @Override
    public String memberType() {
        return "REGULAR";
    }

    @Override
    public long discount(long amount) {
        return 0L;
    }
}
