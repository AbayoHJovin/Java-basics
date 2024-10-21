package Student;

import java.util.Date;

public class Record extends Person { private String studentClass;
public Record(){}
    public Record(String firstName, String lastName, Date dob, String email, String studentClass) {
        super(firstName, lastName,email, dob);
        this.email = email;
        this.studentClass = studentClass;
    }
    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void getTableHeader() {
        System.out.printf("%-15s %-15s %-25s %-15s %-10s%n",
                "First Name", "Last Name", "Email", "DOB", "Class");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-25s %-15s %-10s%n", firstName, lastName, email, dob, studentClass);
    }



}
