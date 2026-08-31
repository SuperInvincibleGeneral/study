import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex12Test {

    public static void main(String[] args) {
        Assert.suite("課題12 Comparable と Comparator", () -> {

            // 給与は long。int に収まらない大きさを混ぜてある。
            // 氏名・部署名は英字（並び順が辞書順で分かりやすいように）。
            Employee sato = new Employee("Sato", "Sales", 4_000_000L);
            Employee ito = new Employee("Ito", "Dev", 6_000_000L);
            Employee ueda = new Employee("Ueda", "Dev", 6_000_000L);
            Employee endo = new Employee("Endo", "Sales", 3_000_000_000L);  // 特別報酬（int の上限を超える）
            Employee okada = new Employee("Okada", "Admin", 2_500_000_000L);

            List<Employee> source = new ArrayList<>(Arrays.asList(sato, ito, ueda, endo, okada));
            List<Employee> snapshot = new ArrayList<>(source);

            // --- compareTo 単体（オーバーフローの確認）---
            Assert.isTrue("compareTo: 3,000,000,000 は 4,000,000 より大きい ★ここが本番",
                    endo.compareTo(sato) > 0);
            Assert.isTrue("compareTo: 4,000,000 は 3,000,000,000 より小さい",
                    sato.compareTo(endo) < 0);
            Assert.eq("compareTo: 同額なら 0", 0, ito.compareTo(ueda));

            // --- bySalaryAsc ---
            List<Employee> asc = EmployeeSorter.bySalaryAsc(source);
            Assert.eq("bySalaryAsc: 給与の昇順", "[Sato, Ito, Ueda, Okada, Endo]", asc.toString());
            Assert.eq("bySalaryAsc: 元のリストを変更していない ★ここが本番",
                    snapshot.toString(), source.toString());

            // --- bySalaryDescThenName ---
            List<Employee> desc = EmployeeSorter.bySalaryDescThenName(source);
            Assert.eq("bySalaryDescThenName: 給与降順→同額は名前昇順",
                    "[Endo, Okada, Ito, Ueda, Sato]", desc.toString());
            Assert.eq("bySalaryDescThenName: 元のリストを変更していない",
                    snapshot.toString(), source.toString());

            // --- byDepartmentThenSalaryDesc ---
            List<Employee> byDept = EmployeeSorter.byDepartmentThenSalaryDesc(source);
            Assert.eq("byDepartmentThenSalaryDesc: 部署昇順(Admin,Dev,Sales)→給与降順",
                    "[Okada, Ito, Ueda, Endo, Sato]", byDept.toString());
            Assert.eq("byDepartmentThenSalaryDesc: 元のリストを変更していない",
                    snapshot.toString(), source.toString());

            // --- 空リスト・1件でも落ちないこと ---
            Assert.eq("空リストでも動く", "[]",
                    EmployeeSorter.bySalaryDescThenName(new ArrayList<Employee>()).toString());
            Assert.eq("1件でも動く", "[Sato]",
                    EmployeeSorter.byDepartmentThenSalaryDesc(new ArrayList<>(Arrays.asList(sato))).toString());
        });
    }
}
