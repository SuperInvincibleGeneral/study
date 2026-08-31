/**
 * 課題08: テキスト分割。
 * 「区切り文字を変えただけなのに動かない」「末尾の空項目が消える」という不具合がある。
 */
public class CsvParser {

    /** "2024.05.01" のようなドット区切りを分割する。 -> ["2024", "05", "01"] */
    public String[] splitByDot(String text) {
        return text.split(".");
    }

    /** "A|B|C" のようなパイプ区切りを分割する。 -> ["A", "B", "C"] */
    public String[] splitByPipe(String text) {
        return text.split("|");
    }

    /**
     * CSV の1行をカンマで分割する。
     * 末尾に空の項目があっても、その数だけ要素を返すこと。
     * 例) "a,b,," -> ["a", "b", "", ""]
     */
    public String[] splitCsv(String line) {
        return line.split(",");
    }

    /** 区切り文字がどんな文字でも安全に分割する（正規表現として解釈させない）。 */
    public String[] splitBy(String text, String delimiter) {
        return text.split(delimiter);
    }
}
