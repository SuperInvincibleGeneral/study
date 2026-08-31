import java.util.ArrayList;
import java.util.List;

public class Ex15Test {

    public static void main(String[] args) {
        Assert.suite("課題15 継承とオーバーライド", () -> {

            EmailNotification mail = new EmailNotification("請求書", "a@example.com", "ご確認ください");
            SmsNotification sms = new SmsNotification("再配達", "090-0000-0000");

            Assert.eq("Email: render", "ご確認ください [Email]", mail.render());
            Assert.eq("Email: getTitle", "請求書", mail.getTitle());
            Assert.eq("Email: getRecipient", "a@example.com", mail.getRecipient());
            Assert.eq("Email: summary の件名が空になっていない ★ここが本番",
                    "[請求書] to a@example.com : ご確認ください [Email]", mail.summary());

            Assert.eq("SMS: render", "再配達 [SMS]", sms.render());
            Assert.eq("SMS: getTitle", "再配達", sms.getTitle());
            Assert.eq("SMS: summary",
                    "[再配達] to 090-0000-0000 : 再配達 [SMS]", sms.summary());

            // 多態: Notification 型の変数から呼んでも、実際の型の render が呼ばれること
            List<Notification> all = new ArrayList<>();
            all.add(mail);
            all.add(sms);

            StringBuilder sb = new StringBuilder();
            for (Notification n : all) {
                sb.append(n.summary()).append("\n");
            }
            Assert.eq("多態: Notification 型で扱っても正しく描画される ★ここが本番",
                    "[請求書] to a@example.com : ご確認ください [Email]\n"
                            + "[再配達] to 090-0000-0000 : 再配達 [SMS]\n",
                    sb.toString());

            Notification asBase = mail;
            Assert.eq("親型の変数から render を呼んでも子の実装が動く",
                    "ご確認ください [Email]", asBase.render());

            Assert.isTrue("EmailNotification は Notification のサブクラス",
                    Notification.class.isAssignableFrom(EmailNotification.class));
            Assert.isTrue("SmsNotification は Notification のサブクラス",
                    Notification.class.isAssignableFrom(SmsNotification.class));
        });
    }
}
