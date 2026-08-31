import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * 課題14 解答例。
 *
 * ポイント:
 *  1. Writer / OutputStream は内部にバッファを持っている。close（または flush）しないと
 *     書いた内容がファイルに反映されないことがある。「書いたはずなのに空」の正体はこれ。
 *  2. close を確実に行うには try-with-resources を使う。
 *     try (AutoCloseable r = ...) { ... } と書けば、例外が出ても必ず閉じられる。
 *     自分で close() を書く方式は、途中で例外が出ると閉じられずリークする。
 *  3. FileWriter は既定で「上書き」。追記したいなら append フラグ（第2引数 true）を渡す。
 *     Files.write なら StandardOpenOption.APPEND を指定する。
 *  4. 文字コードを指定しないと実行環境依存になる。必ず明示する（ここでは UTF-8）。
 *  5. 「ファイルが無い」を握りつぶして null を返すのは最悪。
 *     チェック例外 IOException を非チェック例外 UncheckedIOException に包み直して投げる。
 *
 *  なお java.nio.file.Files を使えば、この程度の読み書きは1行で書けて
 *  close 漏れも起きない。まずは標準ライブラリに用意が無いかを探す癖をつけること。
 */
public class LogFile {

    /** 全行を読み込んで返す。ファイルが無い場合は UncheckedIOException を投げること。 */
    public static List<String> readLines(Path path) {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("読み込みに失敗しました: " + path, e);
        }
    }

    /** 全行を書き出す（既存の内容は消える）。 */
    public static void write(Path path, List<String> lines) {
        try {
            Files.write(path, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("書き込みに失敗しました: " + path, e);
        }
    }

    /** 1行追記する（既存の内容は残す）。 */
    public static void append(Path path, String line) {
        try {
            Files.write(path, List.of(line), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException("追記に失敗しました: " + path, e);
        }
    }

    /** レベルが ERROR の行数を数える。 */
    public static int countErrors(Path path) {
        int count = 0;
        for (String line : readLines(path)) {
            int tab = line.indexOf('\t');
            String level = (tab < 0) ? line : line.substring(0, tab);
            if ("ERROR".equals(level)) {
                count++;
            }
        }
        return count;
    }
}
