package Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        RecordManagement rc = new RecordManagement();
        Scanner input = new Scanner(System.in);
        String option;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        menu();
        while (true) {
            System.out.print("Please choose an option(Type help to see our menu): ");
            if (!input.hasNextLine()) {
                System.out.println("Invalid input! Please enter a correct input!" +
                        ".");
                input.next();
                break;
            }
            option = input.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter student's first name: ");
                    String firstName = input.nextLine();
                    System.out.print("Enter student's last name: ");
                    String lastName = input.nextLine();

                    String email = "";
                    boolean checkEmail = false;
                    while (!checkEmail) {
                        System.out.print("Enter the email of the student: ");
                        email = input.nextLine();
                        if (!rc.checkEmail(email)) {
                            checkEmail = true;
                        } else {
                            System.out.println("The user with this email already exists.");
                        }
                    }

                    System.out.print("Enter student's class: ");
                    String studentClass = input.nextLine();

                    Date date = null;
                    while (date == null) {
                        System.out.print("Enter your date of birth (yyyy-MM-dd): ");
                        String dateInput = input.nextLine();

                        try {
                            date = dateFormat.parse(dateInput);
                        } catch (ParseException e) {
                            System.out.println("Invalid Date Format. Please use the format yyyy-MM-dd.");
                        }
                    }

                    Record record = new Record(firstName, lastName, date, email, studentClass);
                    rc.add(record);
                    break;

                case "2":
                    System.out.println("Here is the list of students...");
                    rc.displayAllStudents();
                    break;

                case "3":
                    System.out.print("Enter a student's email: ");
                    String studentEmail = input.nextLine();
                    rc.searchForStudent(studentEmail);
                    break;

                case "4":
                    String pastEmail = null;
                    boolean validEmail = false;

                    while (!validEmail) {
                        System.out.print("Enter the email of the student you want to update: ");
                        pastEmail = input.nextLine();

                        if (rc.checkEmail(pastEmail)) {
                            validEmail = true;
                        } else {
                            System.out.println("Error: No student with this email found. Please enter a valid email of a student:");
                        }
                    }

                    System.out.print("Enter new student's first name: ");
                    String newFirstName = input.nextLine();
                    System.out.print("Enter new student's last name: ");
                    String newLastName = input.nextLine();
                    System.out.print("Enter the new email of the student: ");
                    String newEmail = input.nextLine();
                    System.out.print("Update the dob of student: ");

                    Date birthDate = null;
                    while (birthDate == null) {
                        System.out.print("Enter your date of birth (yyyy-MM-dd): ");
                        String dateOfBirth = input.nextLine();
                        try {
                            birthDate = dateFormat.parse(dateOfBirth);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
                        }
                    }

                    System.out.print("Enter the student's class: ");
                    String newStudentClass = input.nextLine();

                    Record rec = new Record(newFirstName, newLastName, birthDate, newEmail, newStudentClass);
                    rc.updateStudent(pastEmail, rec);
                    break;

                case "5":
                    System.out.print("Enter the email of a student to be deleted: ");
                    String emailToDelete = input.nextLine();
                    rc.deleteStudent(emailToDelete);
                    break;

                case "6":
                    rc.addCsv();
                    break;

                case "menu":
                case "help":
                    menu();
                    break;

                case "00":
                    System.out.println("Exiting program. Goodbye!");
                    input.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option! Please choose a valid option.");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("\n MENU");
        System.out.println("1. Add Student");
        System.out.println("2. Display all students");
        System.out.println("3. Search for a student");
        System.out.println("4. Update a student");
        System.out.println("5. Delete a student");
        System.out.println("6. Insert a CSV file");
        System.out.println("00. Exit");
    }
}
