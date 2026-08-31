/**
 * 課題03 解答例。
 *
 * ポイント:
 *  - static メソッドは「クラスに属する」ので、インスタンスフィールド(count)を直接触れない。
 *    -> "non-static variable count cannot be referenced from a static context"
 *  - static メソッド main から インスタンスメソッド increment() は直接呼べない。
 *    -> "non-static method increment() cannot be referenced from a static context"
 *    -> main の中で new してインスタンス経由で呼ぶ。
 *  - 「インスタンスごとの値」は普通のフィールド、「クラス全体で1つの値」は static フィールド。
 *    生成数を数えたいのでコンストラクタで static フィールドを増やす。
 */
public class Counter {

    /** クラス全体で共有する「生成された総数」。 */
    private static int created = 0;

    /** インスタンスごとのカウント。 */
    private int count = 0;

    public Counter() {
        created++;
    }

    /** カウントを1増やす。 */
    public void increment() {
        count++;
    }

    /** 現在のカウントを返す。 */
    public int getCount() {
        return count;
    }

    /** 現在のカウントの2倍を返す。 */
    public int doubled() {
        return count * 2;
    }

    /** 生成された Counter の総数を返す。 */
    public int createdCount() {
        return created;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.increment();
        counter.increment();
        System.out.println("count = " + counter.getCount());
    }
}
