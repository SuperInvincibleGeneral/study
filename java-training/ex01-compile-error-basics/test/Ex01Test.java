public class Ex01Test {

    public static void main(String[] args) {
        Assert.suite("課題01 コンパイルエラーの読み方", () -> {
            Greeting g = new Greeting();

            Assert.eq("greet: 名前を挟んだ挨拶文を返す", "こんにちは、山田さん", g.greet("山田"));
            Assert.eq("greet: 別の名前でも動く", "こんにちは、Bobさん", g.greet("Bob"));

            Assert.eq("calcTotal: 120円 x 3個 = 360", 360, g.calcTotal(120, 3));
            Assert.eq("calcTotal: 0個なら 0", 0, g.calcTotal(120, 0));

            Assert.eq("describe: 20歳は成人", "成人", g.describe(20));
            Assert.eq("describe: 19歳は未成年", "未成年", g.describe(19));
            Assert.eq("describe: 0歳は未成年", "未成年", g.describe(0));
        });
    }
}
