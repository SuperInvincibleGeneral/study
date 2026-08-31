import java.util.List;

public class Ex09Test {

    public static void main(String[] args) {
        Assert.suite("課題09 繰り返し中の削除", () -> {

            TaskList t1 = new TaskList("[x] 設計", "[ ] 実装", "[x] レビュー", "[ ] テスト");
            t1.removeDone();
            Assert.eq("removeDone: 完了2件を消して 2件残る ★ここが本番", 2, t1.size());
            Assert.eq("removeDone: 残った内容", "[[ ] 実装, [ ] テスト]", t1.all().toString());

            TaskList t2 = new TaskList("[x] A", "[x] B", "[x] C");
            t2.removeDone();
            Assert.eq("removeDone: 全部完了なら 0件", 0, t2.size());

            TaskList t3 = new TaskList("[ ] A", "[ ] B");
            t3.removeDone();
            Assert.eq("removeDone: 消すものが無ければそのまま", 2, t3.size());

            // 連続して該当する要素があるとき、インデックスで回すと1個飛ばしになる
            TaskList t4 = new TaskList("あ", "い", "うえお", "か", "きくけこ");
            t4.removeShorterThan(3);
            Assert.eq("removeShorterThan: 連続する短いタスクも漏れなく消える ★ここが本番", 2, t4.size());
            Assert.eq("removeShorterThan: 残った内容", "[うえお, きくけこ]", t4.all().toString());

            TaskList t5 = new TaskList("あ", "い", "う");
            t5.removeShorterThan(3);
            Assert.eq("removeShorterThan: 全部消える", 0, t5.size());

            // 初期タスクを渡して作ったリストにも追加できること
            TaskList t6 = new TaskList("[ ] 既存");
            Assert.doesNotThrow("初期タスク付きで生成しても add できる ★ここが本番", () -> t6.add("[ ] 追加"));
            Assert.eq("add 後の件数", 2, t6.size());

            TaskList t7 = new TaskList();
            t7.add("[ ] X");
            List<String> all = t7.all();
            Assert.eq("引数なしで生成した場合", 1, all.size());

        });
    }
}
