/**
 * 課題18 解答例: 長方形。
 *
 * 不変（immutable）にしたのが要点。
 * 「途中で幅だけ書き換えられる」ことが LSP 違反の原因だったので、
 * 状態を変えるのではなく「新しいインスタンスを返す」形にした。
 * こうすると Square が Rectangle を継承していなくても困らない。
 */
public class Rectangle implements Shape {

    private final long width;
    private final long height;

    public Rectangle(long width, long height) {
        this.width = width;
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    /** 幅だけを差し替えた新しい長方形。 */
    public Rectangle withWidth(long newWidth) {
        return new Rectangle(newWidth, height);
    }

    /** 高さだけを差し替えた新しい長方形。 */
    public Rectangle withHeight(long newHeight) {
        return new Rectangle(width, newHeight);
    }

    @Override
    public long area() {
        return width * height;
    }

    @Override
    public Shape scaled(int factor) {
        return new Rectangle(width * factor, height * factor);
    }

    @Override
    public String toString() {
        return "Rectangle(" + width + "x" + height + ")";
    }
}
