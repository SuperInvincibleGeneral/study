import java.util.List;

/**
 * 課題19 解答例: 印刷だけを行う処理。
 *
 * 引数の型が Printer なので、
 *  - スキャンや FAX の存在を知らずに済む
 *  - 印刷しかできない機種も、複合機も、どちらも渡せる
 * 「必要な能力だけを要求する」のが ISP の実利。
 */
public class PrintJob {

    private final Printer printer;

    public PrintJob(Printer printer) {
        this.printer = printer;
    }

    public List<String> printAll(List<String> documents) {
        return documents.stream().map(printer::print).toList();
    }
}
