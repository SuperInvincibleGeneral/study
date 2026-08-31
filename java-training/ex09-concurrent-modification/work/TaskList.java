import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 課題09: タスク一覧。
 * 「削除処理を実行すると例外で落ちる」「初期データを渡すと追加できない」という不具合がある。
 */
public class TaskList {

    private List<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** 初期タスクを与えて生成する。生成後も追加・削除できること。 */
    public TaskList(String... initialTasks) {
        this.tasks = Arrays.asList(initialTasks);
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
        for (String task : tasks) {
            if (task.startsWith("[x]")) {
                tasks.remove(task);
            }
        }
    }

    /** 文字数が length 文字未満のタスクをすべて削除する。 */
    public void removeShorterThan(int length) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).length() < length) {
                tasks.remove(i);
            }
        }
    }
}
