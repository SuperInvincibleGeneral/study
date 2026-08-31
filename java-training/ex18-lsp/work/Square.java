/**
 * 課題18: 正方形。
 *
 * 「正方形は長方形の一種だから」と Rectangle を継承したが、
 * 幅を変えたら高さも変わってしまうため、Rectangle として扱うと計算結果が壊れる。
 *
 *   Rectangle r = new Square(5);
 *   r.setWidth(10);
 *   r.setHeight(4);
 *   r.area();   // 40 になってほしいのに 16 になる
 */
public class Square extends Rectangle {

    public Square(long side) {
        super(side, side);
    }

    @Override
    public void setWidth(long width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(long height) {
        this.width = height;
        this.height = height;
    }
}
