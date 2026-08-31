import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 課題12 解答例（EmployeeSorter）。
 *
 * ポイント:
 *  1. Collections.sort(list) / list.sort(null) は渡されたリスト自体を並べ替える（破壊的）。
 *     「元のリストは変更しない」仕様なら、まずコピーを作ってから並べ替える。
 *  2. 「基本の並び順」は Comparable#compareTo、
 *     「場面ごとの並び順」は Comparator で外から与える、と役割が分かれている。
 *  3. 複数キーの並べ替えは Comparator.comparing(...).thenComparing(...) で宣言的に書ける。
 *     降順にしたいキーには reversed() ではなく Comparator.reverseOrder() を
 *     そのキーに対して指定する（reversed() は「それまでの条件すべて」を反転させるので注意）。
 */
public class EmployeeSorter {

    /** 給与の昇順に並べ替える（元のリストは変更しない）。 */
    public static List<Employee> bySalaryAsc(List<Employee> employees) {
        List<Employee> copy = new ArrayList<>(employees);
        copy.sort(Comparator.naturalOrder());
        return copy;
    }

    /** 給与の降順、給与が同じなら名前の昇順。 */
    public static List<Employee> bySalaryDescThenName(List<Employee> employees) {
        List<Employee> copy = new ArrayList<>(employees);
        copy.sort(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder())
                .thenComparing(Employee::getName));
        return copy;
    }

    /** 部署名の昇順、同じ部署なら給与の降順。 */
    public static List<Employee> byDepartmentThenSalaryDesc(List<Employee> employees) {
        List<Employee> copy = new ArrayList<>(employees);
        copy.sort(Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary, Comparator.reverseOrder()));
        return copy;
    }
}
