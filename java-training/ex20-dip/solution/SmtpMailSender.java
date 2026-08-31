/** 実際にメールを送るクラス（本番用）。 */
public class SmtpMailSender {

    public void send(String to, String subject, String body) {
        throw new IllegalStateException("テスト環境から本番のメール送信が呼ばれました: " + to);
    }
}
