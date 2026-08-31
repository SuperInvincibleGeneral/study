import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ex11Test {

    public static void main(String[] args) {
        Assert.suite("課題11 equals と hashCode の契約", () -> {

            Book a1 = new Book("978-4-00-000000-1", "リーダブルコード", 2640);
            Book a2 = new Book("978-4-00-000000-1", "リーダブルコード（第2刷）", 2900);
            Book b = new Book("978-4-00-000000-2", "テスト駆動開発", 3520);

            // --- equals の契約 ---
            Assert.isTrue("反射性: 自分自身と等しい", a1.equals(a1));
            Assert.isTrue("ISBN が同じなら等しい ★ここが本番", a1.equals(a2));
            Assert.isTrue("対称性: a2.equals(a1) も true", a2.equals(a1));
            Assert.isFalse("ISBN が違えば等しくない", a1.equals(b));
            Assert.isFalse("null とは等しくない", a1.equals(null));
            Assert.isFalse("違う型とは等しくない", a1.equals("978-4-00-000000-1"));

            // --- hashCode の契約 ---
            Assert.isTrue("equals が true なら hashCode も一致 ★ここが本番",
                    a1.hashCode() == a2.hashCode());
            Assert.isTrue("同じインスタンスの hashCode は毎回同じ",
                    a1.hashCode() == a1.hashCode());

            // --- コレクションでの振る舞い ---
            Set<Book> set = new HashSet<>();
            set.add(a1);
            set.add(a2);
            set.add(b);
            Assert.eq("HashSet: 同じ ISBN は重複しないので 2件 ★ここが本番", 2, set.size());
            Assert.isTrue("HashSet: contains で見つかる", set.contains(new Book("978-4-00-000000-2", "別題", 0)));

            Map<Book, Integer> stock = new HashMap<>();
            stock.put(a1, 5);
            Assert.eq("HashMap: 同じ ISBN の別インスタンスで取り出せる ★ここが本番",
                    Integer.valueOf(5), stock.get(a2));
            stock.put(a2, 3);
            Assert.eq("HashMap: 同じキーとみなされ上書きされる", 1, stock.size());

            List<Book> list = new ArrayList<>();
            list.add(a1);
            Assert.isTrue("List#contains でも見つかる", list.contains(a2));
            Assert.eq("List#indexOf でも見つかる", 0, list.indexOf(a2));

            Assert.eq("toString は変更しないこと", "リーダブルコード(978-4-00-000000-1)", a1.toString());
        });
    }
}
