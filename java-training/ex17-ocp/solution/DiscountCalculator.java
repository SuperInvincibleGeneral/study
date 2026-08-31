import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 課題17 解答例。
 *
 * ポイント:
 *  - 会員種別が増えても、このクラスのコードは1文字も変わらない（修正に対して閉じている）。
 *  - 新しい種別は DiscountPolicy を実装して register するだけ（拡張に対して開いている）。
 *  - if / else や switch による「種別での分岐」が増え続けているコードは、
 *    たいてい OCP 違反のサイン。分岐の代わりに「実装の差し替え」で表現できないか考える。
 *  - なお種別が絶対に増えない・固定であると分かっているなら enum + switch のほうが簡潔。
 *    「増える見込みがあるか」で選ぶこと。何でもインターフェースにするのは過剰設計。
 */
public class DiscountCalculator {

    private final Map<String, DiscountPolicy> policies = new LinkedHashMap<>();

    public DiscountCalculator() {
        register(new RegularPolicy());
        register(new SilverPolicy());
        register(new GoldPolicy());
    }

    /** 割引方針を登録する。同じ会員種別なら後から登録したもので上書きする。 */
    public void register(DiscountPolicy policy) {
        policies.put(policy.memberType(), policy);
    }

    /** 割引後の支払金額。未知の会員種別は割引なし。 */
    public long priceFor(String memberType, long amount) {
        DiscountPolicy policy = policies.get(memberType);
        if (policy == null) {
            return amount;
        }
        return amount - policy.discount(amount);
    }
}
