/**
 * 課題15 解答例（SMS 通知）。
 *
 * 直した点:
 *  - render(String prefix) は引数が違うので、親の render() のオーバーライドにならない
 *    （これは「オーバーロード」）。@Override を付けていたおかげで
 *    "method does not override or implement a method from a supertype" と教えてくれた。
 *    @Override が無ければ、コンパイルは通り実行時に abstract 未実装エラーになるまで気づけない。
 */
public class SmsNotification extends Notification {

    public SmsNotification(String title, String phoneNumber) {
        super(title, phoneNumber);
    }

    @Override
    public String render() {
        return title + " [SMS]";
    }
}
