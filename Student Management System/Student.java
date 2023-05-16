public class Student {
    private String campus;
    private String faculty;
    private String name;
    private String id;
    private String status;

    public Student(String campus, String faculty, String name, String id, String status) {
        this.campus = campus;
        this.faculty = faculty;
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public String getCampus() {
        return campus;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return String.format("%s  %s  %-12s  %s  %s", campus, faculty, name, id, status);
    }
}