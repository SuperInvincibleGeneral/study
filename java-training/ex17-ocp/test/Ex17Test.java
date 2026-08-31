public class Ex17Test {

    /**
     * ★ このクラスがこの課題の主役。
     * 「新しい会員種別を追加するとき、DiscountCalculator を変更しなくてよいか」を検証する。
     * DiscountCalculator 側に "PLATINUM" と書いてはいけない。
     */
    static class PlatinumPolicy implements DiscountPolicy {
        @Override
        public String memberType() {
            return "PLATINUM";
        }

        @Override
        public long discount(long amount) {
            return amount * 20 / 100;
        }
    }

    /** 期間限定キャンペーン（一律500円引き）も、同じ仕組みで足せること。 */
    static class CampaignPolicy implements DiscountPolicy {
        @Override
        public String memberType() {
            return "CAMPAIGN";
        }

        @Override
        public long discount(long amount) {
            return Math.min(500L, amount);
        }
    }

    public static void main(String[] args) {
        Assert.suite("課題17 開放閉鎖の原則(OCP)", () -> {

            DiscountCalculator calc = new DiscountCalculator();

            // --- 既存の会員種別 ---
            Assert.eq("REGULAR: 割引なし", 10000L, calc.priceFor("REGULAR", 10000L));
            Assert.eq("SILVER: 5%引き", 9500L, calc.priceFor("SILVER", 10000L));
            Assert.eq("GOLD: 10%引き", 9000L, calc.priceFor("GOLD", 10000L));
            Assert.eq("未知の種別は割引なし", 10000L, calc.priceFor("UNKNOWN", 10000L));
            Assert.eq("null も割引なし", 10000L, calc.priceFor(null, 10000L));
            Assert.eq("金額 0 でも落ちない", 0L, calc.priceFor("GOLD", 0L));

            // --- ★ 既存クラスを変更せずに新しい種別を足せること ---
            calc.register(new PlatinumPolicy());
            Assert.eq("PLATINUM: 20%引き ★ここが本番", 8000L, calc.priceFor("PLATINUM", 10000L));
            Assert.eq("PLATINUM を足しても GOLD は影響を受けない", 9000L, calc.priceFor("GOLD", 10000L));

            calc.register(new CampaignPolicy());
            Assert.eq("CAMPAIGN: 一律500円引き", 9500L, calc.priceFor("CAMPAIGN", 10000L));
            Assert.eq("CAMPAIGN: 金額が500未満なら全額引き", 0L, calc.priceFor("CAMPAIGN", 300L));

            // --- ポリシー単体でもテストできること ---
            Assert.eq("GoldPolicy#discount", 1000L, new GoldPolicy().discount(10000L));
            Assert.eq("GoldPolicy#memberType", "GOLD", new GoldPolicy().memberType());
            Assert.eq("SilverPolicy#discount", 500L, new SilverPolicy().discount(10000L));
            Assert.eq("RegularPolicy#discount", 0L, new RegularPolicy().discount(10000L));

            // --- 別インスタンスに登録の影響が漏れないこと ---
            DiscountCalculator another = new DiscountCalculator();
            Assert.eq("新しく作った計算機には PLATINUM は登録されていない",
                    10000L, another.priceFor("PLATINUM", 10000L));
        });
    }
}
