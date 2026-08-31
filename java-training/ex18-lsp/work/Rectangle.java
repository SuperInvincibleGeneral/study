/**
 * 課題18: 長方形。
 */
public class Rectangle {

    protected long width;
    protected long height;

    public Rectangle(long width, long height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    public long area() {
        return width * height;
    }
}
