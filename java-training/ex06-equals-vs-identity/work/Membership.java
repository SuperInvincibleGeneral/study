import java.util.List;

/**
 * 課題06: 会員照合。
 * 手元で試すとうまく動くのに、本番データ（大きなID・外部から読み込んだ文字列）で
 * 突然 false になる、という不具合が報告されている。
 */
public class Membership {

    /** 2つの会員IDが同じ値か。 */
    public boolean sameId(Integer a, Integer b) {
        return a == b;
    }

    /** 2つの会員名が同じ文字列か。両方 null なら true、片方だけ null なら false。 */
    public boolean sameName(String a, String b) {
        return a == b;
    }

    /** リストに target と同じ名前が含まれるか。 */
    public boolean contains(List<String> names, String target) {
        for (String name : names) {
            if (name == target) {
                return true;
            }
        }
        return false;
    }

    /** 名前を大文字小文字を無視して比較する。 */
    public boolean sameNameIgnoreCase(String a, String b) {
        return a.toUpperCase() == b.toUpperCase();
    }
}
