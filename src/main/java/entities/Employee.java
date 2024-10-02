package entities;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Department department;
    private Job job;

    public Employee(){
    }

    public Employee(String name, String email, String phone, Department department, Job job) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department.toString() +
                ", job=" + job.toString() +
                '}';
    }
}
