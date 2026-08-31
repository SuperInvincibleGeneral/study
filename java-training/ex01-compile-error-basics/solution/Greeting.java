/**
 * 課題01 解答例。
 *
 * 直した3点:
 *  1. 文末のセミコロン欠落 -> "';' expected"
 *  2. int 型の計算結果を String 変数に代入していた -> "incompatible types: int cannot be converted to String"
 *  3. if を通らなかった場合に return が無い -> "missing return statement"
 */
public class Greeting {

    /** 「こんにちは、○○さん」を返す。 */
    public String greet(String name) {
        String message = "こんにちは、" + name + "さん";
        return message;
    }

    /** 単価 × 個数 の合計金額を返す。 */
    public int calcTotal(int price, int count) {
        int total = price * count;
        return total;
    }

    /** 20歳以上なら "成人"、それ未満なら "未成年" を返す。 */
    public String describe(int age) {
        if (age >= 20) {
            return "成人";
        }
        return "未成年";
    }
}
