import java.util.List;
import java.util.Objects;

/**
 * 課題06 解答例。
 *
 * ポイント:
 *  1. == は「同じ値か」ではなく「同じオブジェクトか（同じ参照か）」を比べる。
 *     値が同じかを比べたいなら equals を使う。
 *  2. Integer は -128〜127 の範囲だけキャッシュされ、同じインスタンスが再利用される。
 *     そのため小さいIDでは == が偶然 true になり、128以上で突然 false になる。
 *     「小さい値では動くのに大きい値で落ちる」現象の正体はこれ。
 *  3. String も、ソースに直接書いたリテラルは定数プールで共有されるため == が偶然通る。
 *     new String(...) やファイル・通信から組み立てた文字列は別インスタンスになる。
 *  4. null が混ざる可能性があるなら Objects.equals が最短で安全。
 */
public class Membership {

    /** 2つの会員IDが同じ値か。 */
    public boolean sameId(Integer a, Integer b) {
        return Objects.equals(a, b);
    }

    /** 2つの会員名が同じ文字列か。両方 null なら true、片方だけ null なら false。 */
    public boolean sameName(String a, String b) {
        return Objects.equals(a, b);
    }

    /** リストに target と同じ名前が含まれるか。 */
    public boolean contains(List<String> names, String target) {
        for (String name : names) {
            if (Objects.equals(name, target)) {
                return true;
            }
        }
        return false;
    }

    /** 名前を大文字小文字を無視して比較する。 */
    public boolean sameNameIgnoreCase(String a, String b) {
        if (a == null || b == null) {
            return a == null && b == null;
        }
        return a.equalsIgnoreCase(b);
    }
}
