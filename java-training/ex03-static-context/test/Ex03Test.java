public class Ex03Test {

    public static void main(String[] args) {
        Assert.suite("課題03 static と インスタンス", () -> {
            Counter a = new Counter();
            Assert.eq("getCount: 初期値は 0", 0, a.getCount());

            a.increment();
            a.increment();
            a.increment();
            Assert.eq("getCount: 3回増やしたら 3", 3, a.getCount());
            Assert.eq("doubled: count の2倍を返す（インスタンスメソッド）", 6, a.doubled());

            Counter b = new Counter();
            Assert.eq("count は インスタンスごとに独立している", 0, b.getCount());
            b.increment();
            Assert.eq("b を増やしても a は変わらない", 3, a.getCount());
            Assert.eq("b は 1", 1, b.getCount());

            Assert.eq("createdCount: 生成数はクラス全体で共有される(a側)", 2, a.createdCount());
            Assert.eq("createdCount: 生成数はクラス全体で共有される(b側)", 2, b.createdCount());

            new Counter();
            Assert.eq("createdCount: さらに生成すると 3", 3, a.createdCount());
        });
    }
}
