/** 課題19: 全部できる複合機。 */
public class MultiFunctionPrinter implements MultiFunctionDevice {

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

    @Override
    public void refillToner() {
    }

    @Override
    public void refillPaper() {
    }
}
