public class Ex05Test {

    public static void main(String[] args) {
        Assert.suite("課題05 NullPointerException", () -> {
            UserDirectory dir = new UserDirectory();
            dir.register("yamada", "yamada@example.com");
            dir.register("admin", "admin@example.com");
            dir.register("sato", "sato@sample.co.jp");

            Assert.eq("domainOf: 登録済みならドメインを返す", "example.com", dir.domainOf("yamada"));
            Assert.eq("domainOf: 未登録なら unknown", "unknown", dir.domainOf("suzuki"));
            Assert.eq("domainOf: null を渡しても unknown", "unknown", dir.domainOf(null));

            Assert.isTrue("isAdmin: admin は true", dir.isAdmin("admin"));
            Assert.isFalse("isAdmin: 一般利用者は false", dir.isAdmin("yamada"));
            Assert.isFalse("isAdmin: 未登録は false（例外にしない）", dir.isAdmin("suzuki"));
            Assert.isFalse("isAdmin: null も false", dir.isAdmin(null));

            Assert.eq("countWithDomain: example.com は 2人", 2, dir.countWithDomain("example.com"));
            Assert.eq("countWithDomain: sample.co.jp は 1人", 1, dir.countWithDomain("sample.co.jp"));
            Assert.eq("countWithDomain: 該当なしは 0人", 0, dir.countWithDomain("nowhere.jp"));

            Assert.eq("displayName: 前後の空白を落とす", "山田", dir.displayName("  山田  "));
            Assert.eq("displayName: null は (名無し)", "(名無し)", dir.displayName(null));
        });
    }
}
