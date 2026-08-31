/**
 * 設定値が不正なときに投げる例外（解答例）。
 *
 * ポイント:
 *  - Exception を継承すると「チェック例外」になり、呼び出し側に try-catch か throws を強制する。
 *    RuntimeException を継承すると「非チェック例外」になり強制されない。
 *    「呼び出し側が回復できるか」で選ぶ。設定ミスはプログラムを止めるべきなので非チェックにした。
 *  - cause を受け取るコンストラクタを必ず用意する。super(message, cause) に渡せば
 *    スタックトレースに "Caused by: java.lang.NumberFormatException ..." が出るようになる。
 */
public class ConfigException extends RuntimeException {

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
