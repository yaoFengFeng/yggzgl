package entity;

public class Wage {
    private int id;
    private String department,title,name,date,userID,note;
    private float basicSalary,bonus,basic_years_salary,count,others;

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getBasic_years_salary() {
        return basic_years_salary;
    }

    public void setBasic_years_salary(float basic_years_salary) {
        this.basic_years_salary = basic_years_salary;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getOthers() {
        return others;
    }

    public void setOthers(float others) {
        this.others = others;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

}
