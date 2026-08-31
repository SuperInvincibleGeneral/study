import java.util.ArrayList;
import java.util.List;

/**
 * 課題02 解答例。
 *
 * "cannot find symbol" の原因は大きく4パターンある。この課題にはそのうち3つが入っていた。
 *  1. import 忘れ            -> symbol: class List / class ArrayList
 *  2. 変数名のタイプミス       -> symbol: variable itms
 *  3. 存在しないメソッド名     -> symbol: method length() / method contain(String)
 *     （List のサイズは size()、含有判定は contains()。配列の length や String の length() と混同しやすい）
 *  4. （今回は無いが）依存ライブラリがクラスパスに無い
 */
public class ShoppingCart {

    private List<String> items = new ArrayList<>();

    /** 商品を1つ追加する。 */
    public void addItem(String item) {
        items.add(item);
    }

    /** 入っている商品の数を返す。 */
    public int count() {
        return items.size();
    }

    /** 商品名をカンマ区切りでつないだ文字列を返す。空なら空文字。 */
    public String summary() {
        return String.join(", ", items);
    }

    /** 指定した商品が入っているか。 */
    public boolean has(String item) {
        return items.contains(item);
    }
}
