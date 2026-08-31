import java.util.HashMap;
import java.util.Map;

/**
 * 課題05 解答例。
 *
 * ポイント:
 *  1. Map#get は「キーが無ければ null を返す」。戻り値をそのまま使う前に必ず確認する。
 *     Java 14 以降の "helpful NullPointerException" は
 *     「どの式が null だったか」までメッセージに出るので、まずそれを読む。
 *  2. 定数と比較するときは 定数.equals(変数) の順にすると、変数が null でも落ちない。
 *     null 安全に比較したいだけなら java.util.Objects#equals を使う。
 *  3. null かもしれない値を扱う分岐は、メソッドの入口でまとめて弾く（ガード節）と読みやすい。
 *  4. Map#getOrDefault を使うと「無ければ既定値」を1行で書ける。
 */
public class UserDirectory {

    private final Map<String, String> emails = new HashMap<>();

    /** 名前とメールアドレスを登録する。 */
    public void register(String name, String email) {
        emails.put(name, email);
    }

    /** メールアドレスのドメイン部分（@ より後ろ）を返す。未登録なら "unknown"。 */
    public String domainOf(String name) {
        String email = emails.get(name);
        if (email == null) {
            return "unknown";
        }
        return domainPart(email);
    }

    /** admin@example.com かどうか。未登録なら false。 */
    public boolean isAdmin(String name) {
        return "admin@example.com".equals(emails.get(name));
    }

    /** 指定ドメインの利用者数を返す。 */
    public int countWithDomain(String domain) {
        int count = 0;
        for (String email : emails.values()) {
            if (email != null && domainPart(email).equals(domain)) {
                count++;
            }
        }
        return count;
    }

    /** 表示用の名前。null が渡されたら "(名無し)" を返す。 */
    public String displayName(String name) {
        if (name == null) {
            return "(名無し)";
        }
        return name.trim();
    }

    private String domainPart(String email) {
        int at = email.indexOf("@");
        return (at < 0) ? "" : email.substring(at + 1);
    }
}
