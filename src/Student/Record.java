package Student;

import java.util.Date;

public class Record extends Person { private String studentClass;

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


    @Override
    public String toString() {
        return String.format("The student %s %s is saved with email %s and is found in %s. The student was born on %s.",
                firstName, lastName, email, studentClass, dob);
    }

}
