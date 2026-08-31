import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 課題09 解答例。
 *
 * ポイント:
 *  1. 拡張for文（for-each）は内部で Iterator を使っている。
 *     繰り返しの途中でコレクション自体を変更すると ConcurrentModificationException になる。
 *     -> 解決策は3つ。 (a) Iterator#remove を使う  (b) removeIf を使う  (c) 削除対象を別リストに貯めて後で removeAll
 *  2. インデックスで回しながら remove すると、後ろの要素が前に詰まって「1個飛ばし」になる。
 *     例外が出ないぶんこちらのほうが厄介。逆順に回すか removeIf を使う。
 *  3. Arrays.asList が返すのは「配列を包んだ固定長のリスト」。
 *     add / remove すると UnsupportedOperationException になる。
 *     可変なリストが欲しいなら new ArrayList<>(...) で作り直す。
 */
public class TaskList {

    private final List<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** 初期タスクを与えて生成する。生成後も追加・削除できること。 */
    public TaskList(String... initialTasks) {
        this.tasks = new ArrayList<>(Arrays.asList(initialTasks));
    }

    public void add(String task) {
        tasks.add(task);
    }

    public List<String> all() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /** 完了済み（"[x]" で始まる）のタスクをすべて削除する。 */
    public void removeDone() {
        tasks.removeIf(task -> task.startsWith("[x]"));
    }

    /** 文字数が length 文字未満のタスクをすべて削除する。 */
    public void removeShorterThan(int length) {
        // removeIf で1行でも書けるが、Iterator を使った書き方も覚えておくこと。
        Iterator<String> it = tasks.iterator();
        while (it.hasNext()) {
            if (it.next().length() < length) {
                it.remove();
            }
        }
    }
}
