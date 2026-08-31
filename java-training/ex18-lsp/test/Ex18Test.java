import java.util.Arrays;
import java.util.List;

public class Ex18Test {

    public static void main(String[] args) {
        Assert.suite("課題18 リスコフの置換原則(LSP)", () -> {

            Rectangle rect = new Rectangle(5, 4);
            Square square = new Square(5);

            Assert.eq("Rectangle: 面積", 20L, rect.area());
            Assert.eq("Square: 面積", 25L, square.area());

            // --- Rectangle だけが持つ操作。Square には無いので壊しようがない ---
            Assert.eq("Rectangle#withWidth: 幅だけ差し替えた新しい図形", 40L, rect.withWidth(10).area());
            Assert.eq("Rectangle#withHeight: 高さだけ差し替え", 50L, rect.withHeight(10).area());
            Assert.eq("Rectangle#withWidth: 元のインスタンスは変わらない ★ここが本番", 20L, rect.area());
            Assert.eq("Rectangle#withWidth を2回つないでも正しい", 40L,
                    rect.withWidth(10).withHeight(4).area());

            // --- ★ 契約テスト: Shape として扱ったとき、すべての実装で同じ性質が成り立つこと ---
            List<Shape> shapes = Arrays.asList(
                    new Rectangle(5, 4),
                    new Rectangle(3, 3),
                    new Square(5),
                    new Square(1));

            for (Shape shape : shapes) {
                long before = shape.area();
                Shape scaled = shape.scaled(2);
                Assert.eq("契約: " + shape + " を2倍したら面積は4倍 ★ここが本番",
                        before * 4, scaled.area());
                Assert.eq("契約: " + shape + " scaled は元の図形を変更しない",
                        before, shape.area());
                Assert.eq("契約: " + shape + " を3倍したら面積は9倍",
                        before * 9, shape.scaled(3).area());
                Assert.eq("契約: " + shape + " を1倍したら面積は同じ",
                        before, shape.scaled(1).area());
            }

            // --- Square が Rectangle のサブタイプでないこと ---
            Assert.isFalse("Square は Rectangle を継承していない ★ここが本番",
                    Rectangle.class.isAssignableFrom(Square.class));
            Assert.isTrue("Rectangle は Shape である", Shape.class.isAssignableFrom(Rectangle.class));
            Assert.isTrue("Square は Shape である", Shape.class.isAssignableFrom(Square.class));

            // --- Shape に setter が生えていないこと（可変にして逃げていないか）---
            boolean hasSetter = false;
            for (java.lang.reflect.Method m : Shape.class.getMethods()) {
                if (m.getName().startsWith("set")) {
                    hasSetter = true;
                }
            }
            Assert.isFalse("Shape に setter を作っていない", hasSetter);
        });
    }
}
