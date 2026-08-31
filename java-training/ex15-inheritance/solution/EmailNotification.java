/**
 * 課題15 解答例（メール通知）。
 *
 * 直した点:
 *  1. Render() -> render()
 *     Java のメソッド名は大文字小文字を区別する。名前が1文字違うだけで
 *     「オーバーライドしたつもりの別メソッド」になり、abstract メソッドは未実装のまま。
 *     -> "EmailNotification is not abstract and does not override abstract method render()"
 *     オーバーライドのつもりなら必ず @Override を付ける。付けておけばコンパイラが誤りを教えてくれる。
 *  2. 親と同じ名前のフィールド title を子でも宣言していた（フィールドの隠蔽 / field hiding）。
 *     フィールドはメソッドと違って多態にならず、「書かれている場所の型」で決まる。
 *     そのため親の summary() は親の title（空文字）を見てしまう。
 *     -> 子のフィールドを消し、super(...) に正しく渡す。
 */
public class EmailNotification extends Notification {

    private final String body;

    public EmailNotification(String title, String to, String body) {
        super(title, to);
        this.body = body;
    }

    @Override
    public String render() {
        return body + " [Email]";
    }
}
