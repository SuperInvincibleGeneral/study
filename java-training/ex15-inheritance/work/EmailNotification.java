/**
 * 課題15: メール通知。
 * コンパイルが通らない。さらに通ったあとも summary() の件名が空になる。
 */
public class EmailNotification extends Notification {

    private String title;
    private final String body;

    public EmailNotification(String title, String to, String body) {
        super("", to);
        this.title = title;
        this.body = body;
    }

    public String Render() {
        return body + " [Email]";
    }
}
