/**
 * 課題20 解答例: 本番用の実装。
 *
 * 抽象（MailGateway）に既存の具体クラス（SmtpMailSender）を適合させている。
 * 既存クラスを書き換えられない場合でも、この薄い層をはさめば抽象に寄せられる。
 */
public class SmtpMailGateway implements MailGateway {

    private final SmtpMailSender sender = new SmtpMailSender();

    @Override
    public void send(String to, String subject, String body) {
        sender.send(to, subject, body);
    }
}
