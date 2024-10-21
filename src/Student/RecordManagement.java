package Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class RecordManagement {
    final String url = "jdbc:mysql://localhost:3309/javastudentmanagement";
    final String username = "root";
    final String password = "";
    public Connection conn;

    public RecordManagement() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            if (conn != null) {
                System.out.println("The connection to the database is created.");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error is caught: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void add(Record record) {
        String query = "INSERT INTO studentrecord (firstName, lastName, email, dob, studentClass) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, record.getFirstName());
            stmt.setString(2, record.getLastName());
            stmt.setString(3, record.getEmail());
            stmt.setDate(4, new Date(record.getDob().getTime()));
            stmt.setString(5, record.getStudentClass());
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding a student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void displayAllStudents() {
        String query = "SELECT * FROM studentrecord";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet res = pstmt.executeQuery();
            Record record= new Record();
            record.getTableHeader();
            while (res.next()) {
                String firstName = res.getString("firstName");
                String lastName = res.getString("lastName");
                String email = res.getString("email");
                Date dob = res.getDate("dob");
                String studentClass = res.getString("studentClass");
                System.out.println(new Record(firstName, lastName, dob, email, studentClass));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void searchForStudent(String email) {
        String query = "SELECT * FROM studentrecord WHERE email=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String studentEmail = rs.getString("email");
                Date dob = rs.getDate("dob");
                String studentClass = rs.getString("studentClass");
                System.out.println("Student found: \n");
                Record record=new Record();
                record.getTableHeader();
                System.out.println(new Record(firstName, lastName, dob, studentEmail, studentClass));
            } else {
                System.out.println("Unknown student with email " + email + "!");
            }
        } catch (SQLException e) {
            System.out.println("Caught an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateStudent(String oldEmail, Record record) {
        if (checkEmail(oldEmail)) {
            String query = "UPDATE studentrecord SET firstName=?, lastName=?, email=?, dob=?, studentClass=? WHERE email=?";
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, record.getFirstName());
                stmt.setString(2, record.getLastName());
                stmt.setString(3, record.getEmail());
                stmt.setDate(4, new Date(record.getDob().getTime()));
                stmt.setString(5, record.getStudentClass());
                stmt.setString(6, oldEmail);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("The student has been updated 🎉🎉 \n");
                } else {
                    System.out.println("No student with this email was found.");
                }
            } catch (SQLException e) {
                System.out.println("Caught an error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No student with this email found.");
        }
    }

    public void deleteStudent(String email) {
        String query = "DELETE FROM studentrecord WHERE email=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The student has been successfully deleted.");
            } else {
                System.out.println("Invalid email of the student.");
            }
        } catch (SQLException e) {
            System.out.println("Caught an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCsv() {
        String file = "C:\\Users\\ABAYO HIRWA JOVIN\\Downloads\\Development\\Java\\First\\src\\students.csv";
        String query = "INSERT INTO studentrecord (firstName, lastName, email, dob, studentClass) VALUES (?, ?, ?, ?, ?)";
        String lineText;

        try {
            conn.setAutoCommit(true);
            PreparedStatement stmt = conn.prepareStatement(query);
            BufferedReader lineReader = new BufferedReader(new FileReader(file));
            lineReader.readLine();

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String firstName = data[0];
                String lastName = data[1];
                String email = data[2];
                Date dob = Date.valueOf(data[3]);
                String studentClass = data[4];

                if (!checkEmail(email)) {
                    stmt.setString(1, firstName);
                    stmt.setString(2, lastName);
                    stmt.setString(3, email);
                    stmt.setDate(4, dob);
                    stmt.setString(5, studentClass);
                    stmt.executeUpdate();
                } else {
                    System.out.println("Duplicate emails not allowed! Skipping the student with email: " + email);
                }
            }

            lineReader.close();
            System.out.println("Only valid data has been inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Error caught: " + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmail(String email) {
        String query = "SELECT * FROM studentrecord WHERE email=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet checkEmail = stmt.executeQuery();
            return checkEmail.next();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
