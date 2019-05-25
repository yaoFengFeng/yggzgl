package entity;

public class Rules {
    private int id;
    private String department,title;
    private float basicSalary,bonus,basicYearsSalary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public float getBasicYearsSalary() {
        return basicYearsSalary;
    }

    public void setBasicYearsSalary(float basicYearsSalary) {
        this.basicYearsSalary = basicYearsSalary;
    }
}
