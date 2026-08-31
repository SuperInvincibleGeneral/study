/**
 * 課題15: SMS 通知。
 * コンパイルが通らない。
 */
public class SmsNotification extends Notification {

    public SmsNotification(String title, String phoneNumber) {
        super(title, phoneNumber);
    }

    @Override
    public String render(String prefix) {
        return prefix + " [SMS]";
    }
}
