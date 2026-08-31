public class Ex02Test {

    public static void main(String[] args) {
        Assert.suite("課題02 cannot find symbol", () -> {
            ShoppingCart cart = new ShoppingCart();

            Assert.eq("count: 初期状態は 0", 0, cart.count());
            Assert.eq("summary: 初期状態は空文字", "", cart.summary());
            Assert.isFalse("has: 空のカゴには何も入っていない", cart.has("りんご"));

            cart.addItem("りんご");
            cart.addItem("みかん");
            cart.addItem("ぶどう");

            Assert.eq("count: 3件追加したら 3", 3, cart.count());
            Assert.eq("summary: カンマ区切りで連結", "りんご, みかん, ぶどう", cart.summary());
            Assert.isTrue("has: 追加した商品は入っている", cart.has("みかん"));
            Assert.isFalse("has: 追加していない商品は入っていない", cart.has("もも"));

            cart.addItem("りんご");
            Assert.eq("count: 同じ商品を追加しても件数は増える", 4, cart.count());
        });
    }
}
