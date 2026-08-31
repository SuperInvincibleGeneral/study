import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 課題14: ログファイルの読み書き。
 * 「書いたはずのファイルが空」「追記したら前の内容が消えた」
 * 「ファイルが無いのに例外も出ず null が返る」という不具合がある。
 *
 * ログの1行は "レベル<TAB>メッセージ" の形式。
 */
public class LogFile {

    /** 全行を読み込んで返す。ファイルが無い場合は UncheckedIOException を投げること。 */
    public static List<String> readLines(Path path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            return null;
        }
    }

    /** 全行を書き出す（既存の内容は消える）。 */
    public static void write(Path path, List<String> lines) {
        try {
            FileWriter writer = new FileWriter(path.toFile());
            for (String line : lines) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 1行追記する（既存の内容は残す）。 */
    public static void append(Path path, String line) {
        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(line);
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** レベルが ERROR の行数を数える。 */
    public static int countErrors(Path path) {
        int count = 0;
        for (String line : readLines(path)) {
            if (line.contains("ERROR")) {
                count++;
            }
        }
        return count;
    }
}
