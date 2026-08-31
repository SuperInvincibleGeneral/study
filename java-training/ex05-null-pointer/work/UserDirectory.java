import java.util.HashMap;
import java.util.Map;

/**
 * 課題05: 利用者名簿。
 * 登録されていない名前を渡すと NullPointerException で落ちる。
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
        return email.substring(email.indexOf("@") + 1);
    }

    /** admin@example.com かどうか。未登録なら false。 */
    public boolean isAdmin(String name) {
        String email = emails.get(name);
        return email.equals("admin@example.com");
    }

    /** 指定ドメインの利用者数を返す。 */
    public int countWithDomain(String domain) {
        int count = 0;
        for (String email : emails.values()) {
            if (domain.equals(email.substring(email.indexOf("@") + 1))) {
                count++;
            }
        }
        return count;
    }

    /** 表示用の名前。null が渡されたら "(名無し)" を返す。 */
    public String displayName(String name) {
        return name.trim();
    }
}
