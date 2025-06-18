public class Student {
    private int rollNo;
    private String name;
    private String course;
    private int year;
    private double gpa;

    public Student(int rollNo, String name, String course, int year, double gpa) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.year = year;
        this.gpa = gpa;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public int getYear() { return year; }
    public double getGpa() { return gpa; }
}