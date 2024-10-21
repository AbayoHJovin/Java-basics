package Student;

import java.util.Date;

public class Person {
    protected String firstName;
    protected String lastName;
    protected Date dob;
    protected String email;
    public Person(){}
    public Person(String firstName, String lastName,String email, Date dob){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.dob=dob;
    }
public Person(String firstName,String lastName,Date dob){
        this.firstName=firstName;
        this.lastName=lastName;
}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }
}
