/** FAX を送れる、という能力だけを表すインターフェース。 */
public interface FaxDevice {
    String fax(String document, String number);
}
