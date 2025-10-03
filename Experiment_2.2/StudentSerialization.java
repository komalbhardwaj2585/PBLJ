import java.io.*;

// Student class must implement Serializable
class Student implements Serializable {
    private int studentID;
    private String name;
    private String grade;

    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public void display() {
        System.out.println("ID: " + studentID + ", Name: " + name + ", Grade: " + grade);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Komal", "A");

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(s1);
            System.out.println("Student object serialized to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("Student object deserialized:");
            s2.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

