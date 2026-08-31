import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Ex14Test {

    public static void main(String[] args) {
        Assert.suite("課題14 リソースの後始末と例外", () -> {

            Path dir = Files.createTempDirectory("ex14");
            dir.toFile().deleteOnExit();
            Path log = dir.resolve("app.log");

            // --- write: 書いた内容が実際にファイルへ反映されていること ---
            LogFile.write(log, Arrays.asList("INFO\t起動しました", "ERROR\t接続に失敗", "WARN\t再試行します"));
            String raw = new String(Files.readAllBytes(log), StandardCharsets.UTF_8);
            Assert.isFalse("write: ファイルが空になっていない ★ここが本番", raw.isEmpty());

            // --- readLines ---
            List<String> lines = LogFile.readLines(log);
            Assert.eq("readLines: 3行読める", 3, lines == null ? -1 : lines.size());
            Assert.eq("readLines: 1行目", "INFO\t起動しました", lines.get(0));
            Assert.eq("readLines: 日本語が化けていない", "ERROR\t接続に失敗", lines.get(1));

            // --- append: 既存の内容が消えないこと ---
            LogFile.append(log, "ERROR\tタイムアウト");
            List<String> appended = LogFile.readLines(log);
            Assert.eq("append: 追記後は4行 ★ここが本番", 4, appended.size());
            Assert.eq("append: 元の1行目が残っている", "INFO\t起動しました", appended.get(0));
            Assert.eq("append: 追記した行が末尾", "ERROR\tタイムアウト", appended.get(3));

            LogFile.append(log, "INFO\t終了しました");
            Assert.eq("append: 2回追記すると5行", 5, LogFile.readLines(log).size());

            // --- countErrors: レベルが ERROR の行だけ数える ---
            Path log2 = dir.resolve("count.log");
            LogFile.write(log2, Arrays.asList(
                    "ERROR\t接続に失敗",
                    "INFO\tERROR という文字を含むが INFO レベルの行",   // これは数えない
                    "ERROR\tタイムアウト",
                    "WARN\t遅延"));
            Assert.eq("countErrors: ERROR レベルは2件 ★ここが本番", 2, LogFile.countErrors(log2));

            // --- 存在しないファイル: 握りつぶさず例外を投げること ---
            Path missing = dir.resolve("no-such-file.log");
            Throwable t = Assert.throwsType("readLines: 無いファイルは UncheckedIOException ★ここが本番",
                    UncheckedIOException.class, () -> LogFile.readLines(missing));
            Assert.isTrue("readLines: 原因例外に NoSuchFileException が入っている",
                    t != null && t.getCause() instanceof NoSuchFileException);

            // --- 空ファイル ---
            Path empty = dir.resolve("empty.log");
            LogFile.write(empty, List.of());
            Assert.eq("write: 空リストなら 0行", 0, LogFile.readLines(empty).size());
            Assert.eq("countErrors: 空ファイルは 0件", 0, LogFile.countErrors(empty));
        });
    }
}
