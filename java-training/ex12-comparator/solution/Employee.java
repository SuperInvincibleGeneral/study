/**
 * 課題12 解答例（Employee）。
 *
 * ポイント:
 *  - compareTo で「引き算」をしてはいけない。
 *    long や大きな int の差は int に収まらず桁あふれ（オーバーフロー）して符号が反転する。
 *    -> Long.compare / Integer.compare を使う。これは「大小を比べる」ための標準メソッド。
 *  - compareTo は「負 / 0 / 正」を返せばよく、差そのものを返す必要はない。
 */
public class Employee implements Comparable<Employee> {

    private final String name;
    private final String department;
    private final long salary;

    public Employee(String name, String department, long salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public long getSalary() {
        return salary;
    }

    /** 給与の昇順。 */
    @Override
    public int compareTo(Employee other) {
        return Long.compare(this.salary, other.salary);
    }

    @Override
    public String toString() {
        return name;
    }
}
