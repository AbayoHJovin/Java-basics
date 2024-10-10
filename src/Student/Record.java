package Student;

import java.util.Date;

public class Record {
    private String names;
    private String email;
    private Date dob;
    private String studentClass;
    public Record(String names,String email,Date dob, String studentClass){
        this.names=names;
        this.email=email;
        this.dob=dob;
        this.studentClass=studentClass;
    }

    public String getNames(){
        return names;
    }
    public void setNames(String names){
        this.names=names;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public Date getDob(){
        return dob;
    }
    public void setDob(Date dob){
        this.dob=dob;
    }
    public String getStudentClass(){
        return studentClass;
    }
    public void setStudentClass(String studentClass){
        this.studentClass=studentClass;
    }
    @Override
    public String toString() {
        return String.format("%-20s %-30s %-15s %-10s",
                        names, email, dob, studentClass);
    }
    public static String getTableHeader(){
        return  String.format("%-20s %-30s %-15s %-10s",
                "Name", "Email", "Date of Birth", "Class") + "\n";
    }

}
