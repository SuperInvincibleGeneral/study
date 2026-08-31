/**
 * 課題12: 社員。
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
        return (int) (this.salary - other.salary);
    }

    @Override
    public String toString() {
        return name;
    }
}
