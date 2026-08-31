/**
 * 課題01: このクラスはコンパイルが通らない。
 * javac が出すエラーメッセージを読んで、3か所を直すこと。
 */
public class Greeting {

    /** 「こんにちは、○○さん」を返す。 */
    public String greet(String name) {
        String message = "こんにちは、" + name + "さん"
        return message;
    }

    /** 単価 × 個数 の合計金額を返す。 */
    public int calcTotal(int price, int count) {
        String total = price * count;
        return total;
    }

    /** 20歳以上なら "成人"、それ未満なら "未成年" を返す。 */
    public String describe(int age) {
        if (age >= 20) {
            return "成人";
        }
    }
}
