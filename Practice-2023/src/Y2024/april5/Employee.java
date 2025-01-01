package Y2024.april5;

public class Employee {
    public static void main(String[] args) {
        Employee e = new Manager();
        System.out.println(e.getName());
    }

    private String getName() {
        return "Employee";
    }
}

class Manager extends Employee{
    public String getName(){
        return "Manager";
    }
}
