import java.util.ArrayList;
import java.util.List;

public class Ex06Test {

    public static void main(String[] args) {
        Assert.suite("課題06 == と equals", () -> {
            Membership m = new Membership();

            // 小さい値は「たまたま」通ってしまう。大きい値でこそ本性が出る。
            Assert.isTrue("sameId: 小さいID(100) 同士", m.sameId(Integer.valueOf(100), Integer.valueOf(100)));
            Assert.isTrue("sameId: 大きいID(1000) 同士 ★ここが本番", m.sameId(Integer.valueOf(1000), Integer.valueOf(1000)));
            Assert.isFalse("sameId: 違うIDは false", m.sameId(Integer.valueOf(1000), Integer.valueOf(1001)));
            Assert.isTrue("sameId: 両方 null なら true", m.sameId(null, null));
            Assert.isFalse("sameId: 片方だけ null なら false", m.sameId(null, Integer.valueOf(1)));

            String literal = "yamada";
            String built = new StringBuilder("yam").append("ada").toString(); // 実行時に組み立てた別インスタンス

            Assert.isTrue("sameName: リテラル同士", m.sameName("yamada", literal));
            Assert.isTrue("sameName: 実行時に組み立てた文字列とも一致 ★ここが本番", m.sameName(literal, built));
            Assert.isFalse("sameName: 違う名前は false", m.sameName("yamada", "sato"));
            Assert.isTrue("sameName: 両方 null なら true", m.sameName(null, null));
            Assert.isFalse("sameName: 片方だけ null なら false", m.sameName("yamada", null));

            List<String> names = new ArrayList<>();
            names.add(new String("yamada"));
            names.add(new String("sato"));
            Assert.isTrue("contains: 同じ内容の別インスタンスを見つけられる ★ここが本番", m.contains(names, "yamada"));
            Assert.isFalse("contains: 無い名前は false", m.contains(names, "suzuki"));
            Assert.isFalse("contains: null を探しても落ちない", m.contains(names, null));

            Assert.isTrue("sameNameIgnoreCase: 大文字小文字を無視", m.sameNameIgnoreCase("Yamada", "yaMADA"));
            Assert.isFalse("sameNameIgnoreCase: 別名は false", m.sameNameIgnoreCase("Yamada", "Sato"));
            Assert.isTrue("sameNameIgnoreCase: 両方 null なら true", m.sameNameIgnoreCase(null, null));
            Assert.isFalse("sameNameIgnoreCase: 片方 null なら false", m.sameNameIgnoreCase(null, "a"));
        });
    }
}
