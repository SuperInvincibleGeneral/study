import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex13Test {

    public static void main(String[] args) {
        Assert.suite("課題13 ジェネリクスとワイルドカード", () -> {

            List<Integer> ints = Arrays.asList(1, 2, 3);
            List<Double> doubles = Arrays.asList(1.5, 2.5);
            List<Number> numbers = Arrays.asList((Number) 10, 20L, 30.0);

            // ★ List<Integer> / List<Double> をそのまま渡せること
            Assert.eq("sum: List<Integer>", 6.0, DataUtils.sum(ints));
            Assert.eq("sum: List<Double>", 4.0, DataUtils.sum(doubles));
            Assert.eq("sum: List<Number>", 60.0, DataUtils.sum(numbers));
            Assert.eq("sum: 空リストは 0", 0.0, DataUtils.sum(new ArrayList<Integer>()));

            Assert.eq("max: List<Integer>", 3.0, DataUtils.max(ints));
            Assert.eq("max: List<Double>", 2.5, DataUtils.max(doubles));
            Assert.eq("max: 空リストは 0", 0.0, DataUtils.max(new ArrayList<Integer>()));

            // ★ 追加先は List<Number> でも受け取れること
            List<Number> dest = new ArrayList<>();
            DataUtils.copyInts(ints, dest);
            Assert.eq("copyInts: List<Integer> から List<Number> へ", "[1, 2, 3]", dest.toString());

            List<Integer> dest2 = new ArrayList<>();
            DataUtils.copyInts(ints, dest2);
            Assert.eq("copyInts: List<Integer> から List<Integer> へ", "[1, 2, 3]", dest2.toString());

            // ★ 戻り値がキャスト無しで受け取れること（原型ではダメ）
            String head = DataUtils.first(Arrays.asList("a", "b"));
            Assert.eq("first: String のリストなら String が返る", "a", head);

            Integer headInt = DataUtils.first(ints);
            Assert.eq("first: Integer のリストなら Integer が返る", Integer.valueOf(1), headInt);

            Assert.eq("first: 空リストは null", null, DataUtils.first(new ArrayList<String>()));

            // ★ newList も型が保たれること
            List<String> made = DataUtils.newList("x", "y", "z");
            Assert.eq("newList: 中身", "[x, y, z]", made.toString());
            String second = made.get(1);
            Assert.eq("newList: get(1) が String としてそのまま使える", "y", second);
            Assert.eq("newList: 型が保たれているので length() が呼べる", 1, second.length());

            List<Integer> madeInts = DataUtils.newList(5, 6);
            int total = madeInts.get(0) + madeInts.get(1);
            Assert.eq("newList: Integer のリストとして計算できる", 11, total);
        });
    }
}
