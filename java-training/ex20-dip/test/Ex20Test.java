import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Ex20Test {

    /** ★ テスト用の偽メールゲートウェイ。実際には送らず、内容を記録するだけ。 */
    static class RecordingMailGateway implements MailGateway {
        final List<String> sent = new ArrayList<>();

        @Override
        public void send(String to, String subject, String body) {
            sent.add(to + "|" + subject + "|" + body);
        }
    }

    public static void main(String[] args) {
        Assert.suite("課題20 依存性逆転の原則(DIP)", () -> {

            // ★ 時刻を固定する。これができないと「注文日」を検証できない。
            Clock fixed = Clock.fixed(Instant.parse("2026-04-01T00:00:00Z"), ZoneId.of("UTC"));
            RecordingMailGateway gateway = new RecordingMailGateway();

            // ★ 依存を外から渡せること（本番のメール送信は一切呼ばれない）
            OrderService service = new OrderService(gateway, fixed);

            Order order = service.placeOrder("customer@example.com", "Java入門", 3000);

            Assert.eq("Order: 宛先", "customer@example.com", order.getCustomerEmail());
            Assert.eq("Order: 商品", "Java入門", order.getItem());
            Assert.eq("Order: 金額", 3000, order.getAmount());
            Assert.eq("Order: 注文日時が Clock から取られている ★ここが本番",
                    "2026-04-01T00:00", order.getOrderedAt().toString());

            Assert.eq("メールが1通だけ送られた ★ここが本番", 1, gateway.sent.size());
            Assert.eq("メールの内容",
                    "customer@example.com|ご注文確認|"
                            + "ご注文ありがとうございます。\n"
                            + "商品: Java入門\n"
                            + "金額: 3000円\n"
                            + "注文日: 2026/04/01",
                    gateway.sent.get(0));

            // --- 別の時刻・別の注文でも動くこと ---
            Clock another = Clock.fixed(Instant.parse("2026-12-24T15:30:00Z"), ZoneId.of("UTC"));
            RecordingMailGateway gateway2 = new RecordingMailGateway();
            OrderService service2 = new OrderService(gateway2, another);

            Order xmas = service2.placeOrder("santa@example.com", "ケーキ", 4800);
            Assert.eq("別の Clock を渡せば別の日付になる",
                    "2026-12-24T15:30", xmas.getOrderedAt().toString());
            Assert.isTrue("本文に注文日 2026/12/24 が入っている",
                    gateway2.sent.get(0).contains("注文日: 2026/12/24"));
            Assert.eq("gateway2 にだけ届いている", 1, gateway2.sent.size());
            Assert.eq("gateway は増えていない", 1, gateway.sent.size());

            // --- 複数回注文したら、その回数だけ送られること ---
            service.placeOrder("a@example.com", "本A", 100);
            service.placeOrder("b@example.com", "本B", 200);
            Assert.eq("3回注文したら3通", 3, gateway.sent.size());
            Assert.isTrue("2通目の宛先", gateway.sent.get(1).startsWith("a@example.com|"));

            // --- OrderService が具体クラスに依存していないこと ---
            for (java.lang.reflect.Field f : OrderService.class.getDeclaredFields()) {
                Assert.isFalse("OrderService は SmtpMailSender を直接フィールドに持たない ★ここが本番",
                        f.getType().getSimpleName().equals("SmtpMailSender"));
                Assert.isFalse("OrderService は SmtpMailGateway（具象）ではなく抽象に依存すること",
                        f.getType().getSimpleName().equals("SmtpMailGateway"));
            }
            Assert.isTrue("MailGateway はインターフェースである", MailGateway.class.isInterface());
        });
    }
}
