/**
 * 課題の答え合わせ用の、ごく小さな検証ヘルパー。
 * テストフレームワーク（JUnit など）は一切使っていない。
 * 新人はこのファイルを編集しないこと。
 */
public final class Assert {

    private static int passed = 0;
    private static int failed = 0;

    private Assert() {
    }

    /** 例外を投げる可能性のある処理ブロック。 */
    public interface Block {
        void run() throws Exception;
    }

    public static void eq(String name, Object expected, Object actual) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        if (ok) {
            ok(name);
        } else {
            ng(name + "  期待値=<" + expected + "> 実際=<" + actual + ">");
        }
    }

    public static void isTrue(String name, boolean actual) {
        eq(name, Boolean.TRUE, actual);
    }

    public static void isFalse(String name, boolean actual) {
        eq(name, Boolean.FALSE, actual);
    }

    public static void arrayEq(String name, Object[] expected, Object[] actual) {
        eq(name, java.util.Arrays.toString(expected), java.util.Arrays.toString(actual));
    }

    public static void intArrayEq(String name, int[] expected, int[] actual) {
        eq(name, java.util.Arrays.toString(expected), java.util.Arrays.toString(actual));
    }

    /**
     * block が expected 型（またはそのサブクラス）の例外を投げることを検証する。
     * 検証に成功した場合は捕まえた例外を返すので、メッセージや原因例外をさらに調べられる。
     */
    public static Throwable throwsType(String name, Class<? extends Throwable> expected, Block block) {
        try {
            block.run();
        } catch (Throwable t) {
            if (expected.isInstance(t)) {
                ok(name);
                return t;
            }
            ng(name + "  期待した例外=<" + expected.getSimpleName()
                    + "> 実際に発生した例外=<" + t.getClass().getName() + ": " + t.getMessage() + ">");
            return t;
        }
        ng(name + "  期待した例外=<" + expected.getSimpleName() + "> だが、例外は発生しなかった");
        return null;
    }

    /** block が例外を投げずに終わることを検証する。 */
    public static void doesNotThrow(String name, Block block) {
        try {
            block.run();
            ok(name);
        } catch (Throwable t) {
            ng(name + "  例外が発生した=<" + t.getClass().getName() + ": " + t.getMessage() + ">");
        }
    }

    private static void ok(String name) {
        passed++;
        System.out.println("  [PASS] " + name);
    }

    private static void ng(String name) {
        failed++;
        System.out.println("  [FAIL] " + name);
    }

    /**
     * テスト一式を実行して結果を出力する。
     * body の途中で予期しない例外が飛んでも、そこで打ち切って結果表示まで進む。
     */
    public static void suite(String title, Block body) {
        System.out.println("=== " + title + " ===");
        try {
            body.run();
        } catch (Throwable t) {
            failed++;
            System.out.println("  [ERROR] 予期しない例外で中断しました: " + t);
            t.printStackTrace(System.out);
            System.out.println("  （↑ このスタックトレースの1行目が、落ちた場所です）");
        }
        report(title);
    }

    /** 結果を出力する。1件でも失敗していれば終了コード 1 で終わる。 */
    public static void report(String title) {
        System.out.println("------------------------------------------------------------");
        System.out.println(title + " : 成功 " + passed + " 件 / 失敗 " + failed + " 件");
        if (failed > 0) {
            System.out.println("=> まだ完了していません。上の [FAIL] を手がかりに直してください。");
            System.out.flush();
            System.exit(1);
        }
        System.out.println("=> 全テスト成功。おつかれさまでした。");
        System.out.flush();
    }
}
