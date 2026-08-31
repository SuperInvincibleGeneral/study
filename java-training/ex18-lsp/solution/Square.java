/**
 * 課題18 解答例: 正方形。
 *
 * Rectangle を継承しない。継承しなければ
 * 「Rectangle として幅と高さを別々に変えられる」という契約を破りようがない。
 * 共通で扱いたい場面は Shape 型で受ければよい。
 */
public class Square implements Shape {

    private final long side;

    public Square(long side) {
        this.side = side;
    }

    public long getSide() {
        return side;
    }

    @Override
    public long area() {
        return side * side;
    }

    @Override
    public Shape scaled(int factor) {
        return new Square(side * factor);
    }

    @Override
    public String toString() {
        return "Square(" + side + ")";
    }
}
