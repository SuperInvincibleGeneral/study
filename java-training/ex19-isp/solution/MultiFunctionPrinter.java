/**
 * 課題19 解答例: 全部できる複合機。
 *
 * 能力ごとのインターフェースを必要なだけ実装する。
 * 「全部入りのインターフェース」を1つ作るのではなく、小さい能力を組み合わせる。
 */
public class MultiFunctionPrinter implements Printer, DocumentScanner, FaxDevice {

    @Override
    public String print(String document) {
        return "印刷: " + document;
    }

    @Override
    public String scan() {
        return "スキャン結果";
    }

    @Override
    public String fax(String document, String number) {
        return "FAX送信: " + document + " -> " + number;
    }
}
