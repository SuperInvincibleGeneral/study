import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 課題20 解答例。
 *
 * ポイント:
 *  1. 依存するものを自分で new せず、外から受け取る（コンストラクタインジェクション）。
 *     これだけで、本番は SmtpMailGateway、テストは記録用の偽物、と差し替えられる。
 *  2. 現在時刻も「依存」である。java.time.Clock を受け取れば、
 *     テストでは Clock.fixed(...) を渡して時刻を固定できる。
 *     LocalDateTime.now() を直接呼ぶコードは、時刻に関する仕様を検証できない。
 *  3. DIP の本質は「インターフェースを挟むこと」そのものではなく、
 *     「抽象を、使う側（上位）の都合で定義すること」。
 *     MailGateway は SMTP の都合ではなく OrderService が必要な形で定義してある。
 *  4. 副作用（送信）を持つ相手を抽象にしておくと、テストが速く・安全になる。
 */
public class OrderService {

    private final MailGateway mailGateway;
    private final Clock clock;

    public OrderService(MailGateway mailGateway, Clock clock) {
        this.mailGateway = mailGateway;
        this.clock = clock;
    }

    public Order placeOrder(String customerEmail, String item, int amount) {
        Order order = new Order(customerEmail, item, amount, LocalDateTime.now(clock));

        String body = "ご注文ありがとうございます。\n"
                + "商品: " + item + "\n"
                + "金額: " + amount + "円\n"
                + "注文日: " + order.getOrderedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        mailGateway.send(customerEmail, "ご注文確認", body);

        return order;
    }
}
