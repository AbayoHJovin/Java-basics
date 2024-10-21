//package Abstraction;
//
//import Student.Person;
//
//import java.util.Date;
//
//public class FootballPerson extends Person {
//    private String team;
//    private int number;
//    private String country;
//    public FootballPerson(){}
//    public FootballPerson(String first, String last, Date dob, String team, String country, int number){
//        super(first,last,dob);
//        this.team=team;
//        this.country=country;
//        this.number=number;
//    }
//
//    public String getTeam() {
//        return team;
//    }
//
//    public void setTeam(String team) {
//        this.team = team;
//    }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    @Override
//    public String toString() {
//        return "FootballPerson{" +
//                "team='" + team + '\'' +
//                ", number=" + number +
//                ", country='" + country + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", dob=" + dob +
//                '}';
//    }
//}
