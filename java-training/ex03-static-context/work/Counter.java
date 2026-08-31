/**
 * 課題03: カウンタ。
 * static と インスタンス の関係を理解していないと通らない。
 */
public class Counter {

    private int count = 0;

    /** カウントを1増やす。 */
    public void increment() {
        count++;
    }

    /** 現在のカウントを返す。 */
    public int getCount() {
        return count;
    }

    /** 現在のカウントの2倍を返す。 */
    public static int doubled() {
        return count * 2;
    }

    /** 生成された Counter の総数を返す（インスタンスごとではなく全体で1つの値）。 */
    public int createdCount() {
        return created;
    }

    private int created = 0;

    public static void main(String[] args) {
        increment();
        increment();
        System.out.println("count = " + getCount());
    }
}
