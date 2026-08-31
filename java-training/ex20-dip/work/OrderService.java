import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 課題20: 注文サービス。
 *
 * 問題:
 *  1. 具体的な送信手段（SmtpMailSender）を自分で new している。
 *     -> テストすると本番のメール送信が呼ばれてしまう。差し替える隙間が無い。
 *  2. 現在時刻を LocalDateTime.now() で直接取っている。
 *     -> 「注文日が正しく入るか」を検証できない（実行するたび結果が変わる）。
 *
 * テストから差し替えられる形に作り替えること。
 */
public class OrderService {

    private final SmtpMailSender mailSender = new SmtpMailSender();

    public Order placeOrder(String customerEmail, String item, int amount) {
        Order order = new Order(customerEmail, item, amount, LocalDateTime.now());

        String body = "ご注文ありがとうございます。\n"
                + "商品: " + item + "\n"
                + "金額: " + amount + "円\n"
                + "注文日: " + order.getOrderedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        mailSender.send(customerEmail, "ご注文確認", body);

        return order;
    }
}
