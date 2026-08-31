/**
 * 課題02: 買い物カゴ。
 * コンパイルエラー "cannot find symbol" が複数出る。原因はそれぞれ違う。
 */
public class ShoppingCart {

    private List<String> items = new ArrayList<>();

    /** 商品を1つ追加する。 */
    public void addItem(String item) {
        itms.add(item);
    }

    /** 入っている商品の数を返す。 */
    public int count() {
        return items.length();
    }

    /** 商品名をカンマ区切りでつないだ文字列を返す。空なら空文字。 */
    public String summary() {
        return String.join(", ", items);
    }

    /** 指定した商品が入っているか。 */
    public boolean has(String item) {
        return items.contain(item);
    }
}
