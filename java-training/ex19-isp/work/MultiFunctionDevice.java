/**
 * 課題19: 複合機のインターフェース。
 *
 * 「オフィス機器は全部これを実装すればいい」と考えて作ったが、
 * 印刷しかできない機種まで scan / fax の実装を強制されている。
 */
public interface MultiFunctionDevice {

    String print(String document);

    String scan();

    String fax(String document, String number);

    void refillToner();

    void refillPaper();
}
