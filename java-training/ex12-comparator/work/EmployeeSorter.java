import java.util.Collections;
import java.util.List;

/**
 * 課題12: 並べ替え。
 * 「給与が高い人が下に来る」「同じ給与だと順番がバラバラ」と苦情が来ている。
 */
public class EmployeeSorter {

    /** 給与の昇順に並べ替える（元のリストは変更しない）。 */
    public static List<Employee> bySalaryAsc(List<Employee> employees) {
        Collections.sort(employees);
        return employees;
    }

    /**
     * 給与の降順、給与が同じなら名前の昇順に並べ替える（元のリストは変更しない）。
     */
    public static List<Employee> bySalaryDescThenName(List<Employee> employees) {
        return null;
    }

    /**
     * 部署名の昇順、同じ部署なら給与の降順に並べ替える（元のリストは変更しない）。
     */
    public static List<Employee> byDepartmentThenSalaryDesc(List<Employee> employees) {
        return null;
    }
}
