import java.util.regex.Pattern;

/**
 * 課題08 解答例。
 *
 * ポイント:
 *  1. String#split の引数は「区切り文字」ではなく「正規表現」である。
 *     "." は正規表現では「任意の1文字」、"|" は「または」を意味する特殊文字。
 *     そのままだと意図しない分割になる。Javadoc に regex と書いてあるかを必ず確認すること。
 *  2. 特殊文字をただの文字として扱うには
 *      - バックスラッシュでエスケープする（Java 文字列中では "\\." のように2重になる）
 *      - Pattern.quote(...) で丸ごとリテラル化する（区切り文字が実行時に決まる場合はこちらが安全）
 *  3. split は既定で「末尾の空文字列」を捨てる。捨てさせないには limit に -1 を渡す。
 */
public class CsvParser {

    /** "2024.05.01" のようなドット区切りを分割する。 */
    public String[] splitByDot(String text) {
        return text.split("\\.", -1);
    }

    /** "A|B|C" のようなパイプ区切りを分割する。 */
    public String[] splitByPipe(String text) {
        return text.split("\\|", -1);
    }

    /** CSV の1行をカンマで分割する。末尾の空項目も残す。 */
    public String[] splitCsv(String line) {
        return line.split(",", -1);
    }

    /** 区切り文字がどんな文字でも安全に分割する（正規表現として解釈させない）。 */
    public String[] splitBy(String text, String delimiter) {
        return text.split(Pattern.quote(delimiter), -1);
    }
}
