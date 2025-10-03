import java.io.*;
import java.util.*;

class Employee implements Serializable {
    int id;
    String name, designation;
    double salary;

    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + 
                           ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManagementSystem {
    static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Add employee and save in file
    private static void addEmployee(Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, designation, salary);

            // Append new employee to file
            ArrayList<Employee> empList = readEmployeeList();
            empList.add(emp);
            saveEmployeeList(empList);

            System.out.println("Employee added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Display employees
    private static void displayEmployees() {
        ArrayList<Employee> empList = readEmployeeList();
        if (empList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("\n--- Employee Records ---");
            for (Employee e : empList) {
                e.display();
            }
        }
    }

    // Read employee list from file
    private static ArrayList<Employee> readEmployeeList() {
        ArrayList<Employee> empList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            empList = (ArrayList<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            // ignore if file not found initially
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    // Save employee list to file
    private static void saveEmployeeList(ArrayList<Employee> empList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(empList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

