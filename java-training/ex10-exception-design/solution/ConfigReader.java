/**
 * 課題10 解答例。
 *
 * ポイント:
 *  1. Integer.parseInt は変換できないと NumberFormatException（非チェック例外）を投げる。
 *     null を渡した場合も NumberFormatException になる（NPE ではない）ことに注意。
 *  2. catch (Exception e) { return 0; } のような「握りつぶし」は最悪の対処。
 *     原因が消え、後続で意味不明な不具合になる。
 *  3. 低レベルの例外は、そのままではなく「業務的に意味のある例外」に包み直して投げる。
 *     このとき必ず cause（原因例外）を渡すこと。スタックトレースに "Caused by:" が出て
 *     調査できるようになる。
 *  4. 「値が無い」は例外ではなく既定値、「値がおかしい」は例外、と方針を分ける。
 */
public class ConfigReader {

    private static final int DEFAULT_PORT = 8080;
    private static final int DEFAULT_TIMEOUT = 30;

    /** ポート番号を読む。 */
    public int readPort(String value) {
        if (isBlank(value)) {
            return DEFAULT_PORT;
        }
        int port = toInt("port", value);
        if (port < 1 || port > 65535) {
            throw new ConfigException("port は 1〜65535 で指定してください: " + value);
        }
        return port;
    }

    /** タイムアウト秒を読む。 */
    public int readTimeout(String value) {
        if (isBlank(value)) {
            return DEFAULT_TIMEOUT;
        }
        int timeout = toInt("timeout", value);
        if (timeout <= 0) {
            throw new ConfigException("timeout は 1 以上で指定してください: " + value);
        }
        return timeout;
    }

    /** 有効フラグを読む。 */
    public boolean readEnabled(String value) {
        if (isBlank(value)) {
            return false;
        }
        String v = value.trim().toLowerCase();
        switch (v) {
            case "true":
            case "yes":
            case "1":
                return true;
            case "false":
            case "no":
            case "0":
                return false;
            default:
                throw new ConfigException("enabled には true/false/yes/no/1/0 を指定してください: " + value);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private int toInt(String key, String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            // 原因例外を必ず渡す。これが無いと「なぜ失敗したか」が追えなくなる。
            throw new ConfigException(key + " は数値で指定してください: " + value, e);
        }
    }
}
