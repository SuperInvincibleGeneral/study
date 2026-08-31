/**
 * 課題15: 通知の基底クラス。
 */
public abstract class Notification {

    protected String title;
    protected String recipient;

    protected Notification(String title, String recipient) {
        this.title = title;
        this.recipient = recipient;
    }

    /** 通知の本文を組み立てる。サブクラスごとに実装する。 */
    public abstract String render();

    /** 「[件名] to 宛先 : 本文」の形で1行にまとめる。 */
    public String summary() {
        return "[" + title + "] to " + recipient + " : " + render();
    }

    public String getTitle() {
        return title;
    }

    public String getRecipient() {
        return recipient;
    }
}
