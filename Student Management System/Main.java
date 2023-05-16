import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
    public static void main(String[] args){
        ArrayList<Student> students = readFile("student.txt");
        
        for(Student student: students){
            System.out.println(student.toString());
        }
    }
    
    private static ArrayList<Student> readFile(String filename) {
    ArrayList<Student> students = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String campus = values[0];
            String faculty = values[1];
            String name = values[2]; 
            String id = values[3];
            String status = values[4];
            Student student = new Student(campus, faculty, name, id, status);
            students.add(student);
        }
    } catch (FileNotFoundException e) {
        System.err.println("File Not Found");
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("You do not have access to read the file");
        e.printStackTrace();
    }
    return students;
   }
}
