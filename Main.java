import models.School;
import models.Student;
import models.Teacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Locale.setDefault(Locale.US);
        School school = new School("Hogwarts");
        int raiseYears = 10;
        double raisePercent = 100;

        loadStudents("src/students.txt", school);
        loadTeachers("src/teachers.txt", school);

        System.out.println(school);

        school.getStudents().forEach(student ->
                System.out.printf("%s %s has a GPA of %.2f%n", student.getName(), student.getSurname(), student.calcGpa()));

        System.out.println();

        school.getTeachers().forEach(teacher -> {
            teacher.giveRaise(raiseYears, raisePercent);
            String raiseStatus = teacher.isRaised(raiseYears) ?
                    String.format("'s salary has raised to %d", teacher.getSalary()) :
                    String.format("'s salary is still the same at %d", teacher.getSalary());
            System.out.printf("%s %s%s%n", teacher.getName(), teacher.getSurname(), raiseStatus);
        });
    }

    private static void loadStudents(String filePath, School school) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            String name = parts[0];
            String surname = parts[1];
            int age = Integer.parseInt(parts[2]);
            String gender = parts[3];
            List<Integer> grades = new ArrayList<>();
            for (int i = 4; i < parts.length; i++) {
                grades.add(Integer.parseInt(parts[i]));
            }
            school.addStudent(new Student(name, surname, age, gender, grades));
        }
    }

    private static void loadTeachers(String filePath, School school) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNext()) {
            school.addTeacher(new Teacher(scanner.next(), scanner.next(), scanner.nextInt(), scanner.next(), scanner.next(), scanner.nextInt(), scanner.nextInt()));
        }
    }
}
