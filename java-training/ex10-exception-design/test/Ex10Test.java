public class Ex10Test {

    public static void main(String[] args) {
        Assert.suite("課題10 例外の設計と例外連鎖", () -> {
            ConfigReader r = new ConfigReader();

            // --- 正常系 ---
            Assert.eq("readPort: 通常の値", 9090, r.readPort("9090"));
            Assert.eq("readPort: 前後に空白があっても読める", 9090, r.readPort("  9090  "));
            Assert.eq("readPort: null は既定値 8080 ★ここが本番", 8080, r.readPort(null));
            Assert.eq("readPort: 空白のみは既定値 8080", 8080, r.readPort("   "));
            Assert.eq("readPort: 境界値 1", 1, r.readPort("1"));
            Assert.eq("readPort: 境界値 65535", 65535, r.readPort("65535"));

            // --- 異常系: 変換できない ---
            Throwable t1 = Assert.throwsType("readPort: 数値でなければ ConfigException",
                    ConfigException.class, () -> r.readPort("abc"));
            Assert.isTrue("readPort: 原因例外に NumberFormatException が入っている ★ここが本番",
                    t1 != null && t1.getCause() instanceof NumberFormatException);
            Assert.isTrue("readPort: メッセージに元の値が入っている",
                    t1 != null && t1.getMessage() != null && t1.getMessage().contains("abc"));

            // --- 異常系: 範囲外 ---
            Assert.throwsType("readPort: 0 は範囲外", ConfigException.class, () -> r.readPort("0"));
            Assert.throwsType("readPort: 65536 は範囲外", ConfigException.class, () -> r.readPort("65536"));
            Assert.throwsType("readPort: 負数は範囲外", ConfigException.class, () -> r.readPort("-1"));

            // --- ConfigException は非チェック例外であること ---
            Assert.isTrue("ConfigException は RuntimeException を継承している ★ここが本番",
                    RuntimeException.class.isAssignableFrom(ConfigException.class));

            // --- readTimeout ---
            Assert.eq("readTimeout: 通常の値", 60, r.readTimeout("60"));
            Assert.eq("readTimeout: null は既定値 30", 30, r.readTimeout(null));
            Assert.eq("readTimeout: 空文字は既定値 30", 30, r.readTimeout(""));
            Assert.throwsType("readTimeout: 数値でなければ ConfigException ★握りつぶし禁止",
                    ConfigException.class, () -> r.readTimeout("いち"));
            Assert.throwsType("readTimeout: 0 以下は ConfigException",
                    ConfigException.class, () -> r.readTimeout("0"));

            // --- readEnabled ---
            Assert.isTrue("readEnabled: true", r.readEnabled("true"));
            Assert.isTrue("readEnabled: TRUE", r.readEnabled("TRUE"));
            Assert.isTrue("readEnabled: yes", r.readEnabled("yes"));
            Assert.isTrue("readEnabled: 1", r.readEnabled("1"));
            Assert.isFalse("readEnabled: false", r.readEnabled("false"));
            Assert.isFalse("readEnabled: no", r.readEnabled("no"));
            Assert.isFalse("readEnabled: 0", r.readEnabled("0"));
            Assert.isFalse("readEnabled: null は既定値 false", r.readEnabled(null));
            Assert.throwsType("readEnabled: 想定外の値は ConfigException ★ここが本番",
                    ConfigException.class, () -> r.readEnabled("ありえない値"));
        });
    }
}
