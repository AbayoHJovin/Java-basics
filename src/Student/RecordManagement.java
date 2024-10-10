package Student;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
public class RecordManagement {
   final String url="jdbc:mysql://localhost:3309/javastudentmanagement";
//final String url = "jdbc:mysql://localhost:3309/java";
    final String username="root";
   final String password="";
   public Connection conn;

   public RecordManagement(){

       try {
           conn= DriverManager.getConnection(url,username,password);
           if(conn !=null){
               System.out.println("The connection to database is created");
           }else{
               System.out.println("Failed to connect with the database");
           }
       }catch(SQLException e){
           System.out.println("An error is caught"+ e.getMessage());
           e.printStackTrace();
       }
   }
   public void add(Record record){
       String query = "INSERT INTO studentrecord (names,email,dob,studentClass) VALUES (?,?,?,?)";
       try {
           PreparedStatement stmt =conn.prepareStatement(query);
           stmt.setString(1,record.getNames());
           stmt.setString(2,record.getEmail());
           stmt.setDate(3,new Date(record.getDob().getTime()));
           stmt.setString(4,record.getStudentClass());
           stmt.executeUpdate();
           System.out.println("Student added successfully");
       }catch(SQLException e){
           System.out.println("Error adding a student:" + e.getMessage());
           e.printStackTrace();
       }
   }
   public void displayAllStudents(){
       String query="SELECT * FROM StudentRecord";
       try{
           PreparedStatement pstmt=conn.prepareStatement(query);
           ResultSet res = pstmt.executeQuery();
           while(res.next()){
               String names=res.getString("names");
               String email=res.getString("email");
               Date dob=res.getDate("dob");
               String studentClass=res.getString("studentClass");
               System.out.println(new Record(names,email,dob,studentClass));
           }
       }catch(SQLException e){
           System.out.println("error"+e.getMessage());
           e.printStackTrace();
       }

   }
   public void searchForStudent(String email){
       String query= "SELECT * FROM StudentRecord WHERE email=?";
       try{
           PreparedStatement stmt=conn.prepareStatement(query);
           stmt.setString(1,email);
           ResultSet rs= stmt.executeQuery();
           if(rs.next()){
           String name = rs.getString("names");
           String studentEmail=rs.getString("email");
           Date dob=rs.getDate("dob");
           String studentClass=rs.getString("studentClass");
               System.out.println("Student found is: \n");
               System.out.println(Record.getTableHeader());
               System.out.println(new Record(name,studentEmail,dob,studentClass));
           }
           else{
               System.out.println("Unknown student with email " + email + "!");
               return;
           }
       }catch(SQLException e){
           System.out.println("Caught an error" +e.getMessage());
           e.printStackTrace();
       }
   }
   public void updateStudent(String oldEmail,Record record){
       if(checkEmail(oldEmail)) {
           String query = "UPDATE StudentRecord SET names=?,email=?,dob=?,studentClass=? WHERE email= ?";
           try {
               PreparedStatement stmt = conn.prepareStatement(query);
               stmt.setString(1, record.getNames());
               stmt.setString(2, record.getEmail());
               stmt.setDate(3, new Date(record.getDob().getTime()));
               stmt.setString(4, record.getStudentClass());
               stmt.setString(5,oldEmail);
               int rowsUpdated = stmt.executeUpdate();
               if (rowsUpdated > 0) {
                   System.out.println("The student is updated 🎉🎉 \n");
               } else {
                   System.out.println("No student with this email is found");
               }
           } catch (SQLException e) {
               System.out.println("Caught an error" + e.getMessage());
               e.printStackTrace();
           }
       }else{
           System.out.println("No student with this email \n");
           System.out.println("Please Enter a valid email of a student :");

       }
   }
   public void deleteStudent(String email){
       String query= "DELETE FROM StudentRecord where email= ?";
       try{
           PreparedStatement stmt = conn.prepareStatement(query);
           stmt.setString(1,email);
           int rowsDeleted= stmt.executeUpdate();
           if(rowsDeleted > 0){
               System.out.println("The student is successfully deleted");
               System.exit(0);
           }else{
               System.out.println("Invalid email of student");
           }
       }catch(SQLException e){
           System.out.println("Caught an error" + e.getMessage());
           e.printStackTrace();
       }
   }
    public void addCsv() {
        String file = "C:\\Users\\ABAYO HIRWA JOVIN\\Downloads\\Development\\Java\\First\\src\\students.csv";
        String query = "INSERT INTO studentrecord (names, email, dob, studentClass) VALUES (?,?,?,?)";
        String lineText = null;

        try {
            conn.setAutoCommit(true);
            PreparedStatement stmt = conn.prepareStatement(query);
            BufferedReader lineReader = new BufferedReader(new FileReader(file));
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String names = data[0];
                String email = data[1];
                Date dob = Date.valueOf(data[2]);
                String studentClass = data[3];

                // Check if email already exists
                if (!checkEmail(email)) {
                    // Email does not exist, so insert the record
                    stmt.setString(1, names);
                    stmt.setString(2, email);
                    stmt.setDate(3, dob);
                    stmt.setString(4, studentClass);
                    stmt.executeUpdate(); // Execute insert immediately
                } else {
                    // Email exists, skip insertion and notify
                    System.out.println("Duplicate emails not allowed! Skipping the student with email: " + email);
                }
            }

            lineReader.close();
            System.out.println("Only valid data have been inserted successfully");

        } catch (SQLException e) {
            System.out.println("Error caught: " + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmail(String email){
       String query = "SELECT * from StudentRecord WHERE email = ?";
       try{
           PreparedStatement stmt = conn.prepareStatement(query);
           stmt.setString(1,email);
           ResultSet checkEmail = stmt.executeQuery();
           return checkEmail.next();
       }catch(SQLException e){
           System.out.println("Error: " + e.getMessage());
           e.printStackTrace();
       }
       return false;
   }
}
