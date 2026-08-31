/**
 * 課題20: 実際にメールを送るクラス（本番用）。
 * このクラスの中身は変更しない。テストで実際に送信されては困る、という点が重要。
 */
public class SmtpMailSender {

    public void send(String to, String subject, String body) {
        // 実際には SMTP サーバへ接続して送信する。
        // テスト環境から呼ぶと本当にメールが飛んでしまう。
        throw new IllegalStateException("テスト環境から本番のメール送信が呼ばれました: " + to);
    }
}
