public class Ex04Test {

    public static void main(String[] args) {
        Assert.suite("課題04 配列の範囲と off-by-one", () -> {
            ScoreBoard board = new ScoreBoard(new int[] {40, 90, 55, 70, 60});

            Assert.eq("max: 最高点は 90", 90, board.max());
            Assert.eq("max: 先頭が最高点でも動く", 90, new ScoreBoard(new int[] {90, 10, 20}).max());
            Assert.eq("max: 末尾が最高点でも動く", 90, new ScoreBoard(new int[] {10, 20, 90}).max());
            Assert.eq("max: 要素1件でも動く", 7, new ScoreBoard(new int[] {7}).max());

            Assert.throwsType("max: 空配列なら IllegalStateException",
                    IllegalStateException.class,
                    () -> new ScoreBoard(new int[0]).max());

            Assert.eq("sumOfLast: 末尾2件は 70+60=130", 130, board.sumOfLast(2));
            Assert.eq("sumOfLast: 末尾1件は 60", 60, board.sumOfLast(1));
            Assert.eq("sumOfLast: 末尾5件は全件 315", 315, board.sumOfLast(5));
            Assert.eq("sumOfLast: 件数を超えたら全件 315", 315, board.sumOfLast(99));
            Assert.eq("sumOfLast: 0件なら 0", 0, board.sumOfLast(0));

            Assert.eq("at: 0番目は 40", 40, board.at(0));
            Assert.eq("at: 4番目は 60", 60, board.at(4));
            Assert.eq("at: 5番目は範囲外なので -1", -1, board.at(5));
            Assert.eq("at: 負の添字も範囲外なので -1", -1, board.at(-1));
        });
    }
}
