/**
 * 課題19 解答例: 印刷しかできない機種。
 *
 * Printer だけを実装するので、scan や fax を「実装しないという判断」を型で表明できる。
 * UnsupportedOperationException を投げるメソッドは1つも無くなった。
 */
public class SimplePrinter implements Printer {

    @Override
    public String print(String document) {
        return "印刷: " + document;
    }
}
