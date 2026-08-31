import java.util.Arrays;
import java.util.List;

public class Ex19Test {

    /**
     * ★ 印刷する能力だけを持つ、テスト用の自作機種。
     * 「印刷しかできない機種を作るのに、scan / fax を実装させられない」ことを検証する。
     */
    static class DummyPrinter implements Printer {
        @Override
        public String print(String document) {
            return "dummy:" + document;
        }
    }

    public static void main(String[] args) {
        Assert.suite("課題19 インターフェース分離の原則(ISP)", () -> {

            SimplePrinter simple = new SimplePrinter();
            MultiFunctionPrinter mfp = new MultiFunctionPrinter();

            Assert.eq("SimplePrinter: 印刷できる", "印刷: 見積書", simple.print("見積書"));
            Assert.eq("MultiFunctionPrinter: 印刷できる", "印刷: 見積書", mfp.print("見積書"));
            Assert.eq("MultiFunctionPrinter: スキャンできる", "スキャン結果", mfp.scan());
            Assert.eq("MultiFunctionPrinter: FAXを送れる",
                    "FAX送信: 注文書 -> 03-0000-0000", mfp.fax("注文書", "03-0000-0000"));

            // --- ★ 使えない機能は「型として持たない」こと ---
            Assert.isTrue("SimplePrinter は Printer である", simple instanceof Printer);
            Assert.isFalse("SimplePrinter は DocumentScanner ではない ★ここが本番",
                    simple instanceof DocumentScanner);
            Assert.isFalse("SimplePrinter は FaxDevice ではない ★ここが本番",
                    simple instanceof FaxDevice);

            Assert.isTrue("MultiFunctionPrinter は Printer である", mfp instanceof Printer);
            Assert.isTrue("MultiFunctionPrinter は DocumentScanner である", mfp instanceof DocumentScanner);
            Assert.isTrue("MultiFunctionPrinter は FaxDevice である", mfp instanceof FaxDevice);

            // --- ★ 印刷だけを要求する処理には、どちらの機種も渡せること ---
            List<String> docs = Arrays.asList("A", "B");
            Assert.eq("PrintJob: 印刷専用機を渡せる", "[印刷: A, 印刷: B]",
                    new PrintJob(simple).printAll(docs).toString());
            Assert.eq("PrintJob: 複合機も渡せる", "[印刷: A, 印刷: B]",
                    new PrintJob(mfp).printAll(docs).toString());
            Assert.eq("PrintJob: 自作の印刷専用実装も渡せる ★ここが本番", "[dummy:A, dummy:B]",
                    new PrintJob(new DummyPrinter()).printAll(docs).toString());
            Assert.eq("PrintJob: 空リストなら空", "[]",
                    new PrintJob(simple).printAll(List.of()).toString());

            // --- ★ 各インターフェースが小さく保たれていること ---
            Assert.eq("Printer のメソッドは1つ", 1, Printer.class.getMethods().length);
            Assert.eq("DocumentScanner のメソッドは1つ", 1, DocumentScanner.class.getMethods().length);
            Assert.eq("FaxDevice のメソッドは1つ", 1, FaxDevice.class.getMethods().length);

            // --- ★ UnsupportedOperationException を投げるメソッドが残っていないこと ---
            for (java.lang.reflect.Method m : SimplePrinter.class.getDeclaredMethods()) {
                Assert.isFalse("SimplePrinter#" + m.getName() + " は例外宣言なしで呼べる形であること",
                        m.getName().equals("scan") || m.getName().equals("fax"));
            }
        });
    }
}
