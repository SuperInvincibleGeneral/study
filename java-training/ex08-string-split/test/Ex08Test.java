public class Ex08Test {

    public static void main(String[] args) {
        Assert.suite("課題08 String#split の正規表現", () -> {
            CsvParser p = new CsvParser();

            Assert.arrayEq("splitByDot: 2024.05.01 ★ここが本番",
                    new String[] {"2024", "05", "01"}, p.splitByDot("2024.05.01"));
            Assert.arrayEq("splitByDot: 区切りが1つ",
                    new String[] {"file", "txt"}, p.splitByDot("file.txt"));

            Assert.arrayEq("splitByPipe: A|B|C ★ここが本番",
                    new String[] {"A", "B", "C"}, p.splitByPipe("A|B|C"));
            Assert.arrayEq("splitByPipe: 空項目も残す",
                    new String[] {"A", "", "C"}, p.splitByPipe("A||C"));

            Assert.arrayEq("splitCsv: 普通の3項目",
                    new String[] {"a", "b", "c"}, p.splitCsv("a,b,c"));
            Assert.arrayEq("splitCsv: 末尾の空項目を残す ★ここが本番",
                    new String[] {"a", "b", "", ""}, p.splitCsv("a,b,,"));
            Assert.arrayEq("splitCsv: 途中の空項目",
                    new String[] {"a", "", "c"}, p.splitCsv("a,,c"));
            Assert.arrayEq("splitCsv: 全部空",
                    new String[] {"", "", ""}, p.splitCsv(",,"));

            Assert.arrayEq("splitBy: 区切り文字がカンマ",
                    new String[] {"a", "b"}, p.splitBy("a,b", ","));
            Assert.arrayEq("splitBy: 区切り文字がドット",
                    new String[] {"a", "b"}, p.splitBy("a.b", "."));
            Assert.arrayEq("splitBy: 区切り文字が括弧 ★ここが本番",
                    new String[] {"a", "b"}, p.splitBy("a(b", "("));
            Assert.arrayEq("splitBy: 区切り文字が複数文字",
                    new String[] {"a", "b"}, p.splitBy("a::b", "::"));
            Assert.arrayEq("splitBy: 区切り文字がバックスラッシュ",
                    new String[] {"a", "b"}, p.splitBy("a\\b", "\\"));
        });
    }
}
