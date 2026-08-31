/**
 * 課題19: 印刷しかできない安価なプリンタ。
 *
 * 使えない機能まで実装させられ、呼ばれると例外で落ちる。
 * このクラスを使う側は「呼んでよいメソッドはどれか」を型から知ることができない。
 */
public class SimplePrinter implements MultiFunctionDevice {

    @Override
    public String print(String document) {
        return "印刷: " + document;
    }

    @Override
    public String scan() {
        throw new UnsupportedOperationException("この機種はスキャンできません");
    }

    @Override
    public String fax(String document, String number) {
        throw new UnsupportedOperationException("この機種はFAXを送れません");
    }

    @Override
    public void refillToner() {
        // 何もしない
    }

    @Override
    public void refillPaper() {
        // 何もしない
    }
}
