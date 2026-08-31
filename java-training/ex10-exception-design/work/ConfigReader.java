/**
 * 課題10: 設定値の読み取り。
 * 設定ファイルの値は文字列で渡ってくる。不正な値のときの振る舞いが決まっていない。
 */
public class ConfigReader {

    /**
     * ポート番号を読む。
     * - null または空白のみ -> 既定値 8080
     * - 数値に変換できない -> ConfigException（原因例外を cause に保持すること）
     * - 1〜65535 の範囲外   -> ConfigException
     * - 前後に空白があっても読めること
     */
    public int readPort(String value) {
        return Integer.parseInt(value);
    }

    /**
     * タイムアウト秒を読む。
     * - null または空白のみ -> 既定値 30
     * - 数値に変換できない -> ConfigException
     * - 0 以下             -> ConfigException
     */
    public int readTimeout(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 有効フラグを読む。
     * - null または空白のみ            -> 既定値 false
     * - "true"/"TRUE"/"yes"/"1"       -> true
     * - "false"/"FALSE"/"no"/"0"      -> false
     * - それ以外                       -> ConfigException
     */
    public boolean readEnabled(String value) {
        return Boolean.parseBoolean(value);
    }
}
