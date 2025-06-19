public class Student {
    private int rollNo;
    private String name;
    private String course;
    private int year;
    private double gpa;

    // Constructor
    public Student(int rollNo, String name, String course, int year, double gpa) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.year = year;
        this.gpa = gpa;
    }

    // No-argument constructor (optional but useful for frameworks)
    public Student() {
    }

    // Getters
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters (For MVC/data binding)
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
