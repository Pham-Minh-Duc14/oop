package hus.oop.students;

import java.util.Objects;

public class Student implements StudentComparable {
    private String id;
    private String lastname;
    private String firstname;
    private int yearOfBirth;
    private double mathsGrade;
    private double physicsGrade;
    private double chemistryGrade;

    // Private constructor to enforce Builder pattern
    private Student() {}

    public static class StudentBuilder {
        private String id;
        private String lastname;
        private String firstname;
        private int yearOfBirth;
        private double mathsGrade;
        private double physicsGrade;
        private double chemistryGrade;

        public StudentBuilder(String id) {
            // TODO: Initialize the mandatory id field
            this.id = id;
        }

        public StudentBuilder withLastname(String lastname) {
            // TODO: Set lastname and return builder
            this.lastname = lastname;
            return this;
        }

        public StudentBuilder withFirstname(String firstname) {
            // TODO: Set firstname and return builder
            this.firstname = firstname;
            return this;
        }

        public StudentBuilder withYearOfBirth(int yearOfBirth) {
            // TODO: Set yearOfBirth and return builder
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public StudentBuilder withMathsGrade(double mathsGrade) {
            // TODO: Set mathsGrade and return builder
            this.mathsGrade = mathsGrade;
            return this;
        }

        public StudentBuilder withPhysicsGrade(double physicsGrade) {
            // TODO: Set physicsGrade and return builder
            this.physicsGrade = physicsGrade;
            return this;
        }

        public StudentBuilder withChemistryGrade(double chemistryGrade) {
            // TODO: Set chemistryGrade and return builder
            this.chemistryGrade = chemistryGrade;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.id = this.id;
            student.lastname = this.lastname;
            student.firstname = this.firstname;
            student.yearOfBirth = this.yearOfBirth;
            student.mathsGrade = this.mathsGrade;
            student.physicsGrade = this.physicsGrade;
            student.chemistryGrade = this.chemistryGrade;
            // Basic validation can be added here if needed
            return student;
        }
    }

    // Getters
    public String getId() {
        // TODO: Return id
        return id;
    }

    public String getLastname() {
        // TODO: Return lastname
        return lastname;
    }

    public String getFirstname() {
        // TODO: Return firstname
        return firstname;
    }

    public int getYearOfBirth() {
        // TODO: Return yearOfBirth
        return yearOfBirth;
    }

    public double getMathsGrade() {
        // TODO: Return mathsGrade
        return mathsGrade;
    }

    public double getPhysicsGrade() {
        // TODO: Return physicsGrade
        return physicsGrade;
    }

    public double getChemistryGrade() {
        // TODO: Return chemistryGrade
        return chemistryGrade;
    }

    public double getAverageGrade() {
        // TODO: Calculate and return the average grade
        // Assuming equal weight for all subjects
        return (mathsGrade + physicsGrade + chemistryGrade) / 3.0;
    }

    /*
     * Mô tả đối tượng Student theo định dạng: Lastname, Firstname, yearOfBirth, averageGradePoint
     */
    @Override
    public String toString() {
        // TODO: Return string representation in the specified format
        // Format: Lastname, Firstname, yearOfBirth, averageGradePoint (rounded to 2 decimal places)
        return String.format("%s, %s, %d, %.2f", lastname, firstname, yearOfBirth, getAverageGrade());
    }

    /*
     * Đưa ra tiêu chí so sánh 2 đối tượng Student, đầu tiên là so sánh theo Firstname, sau đó là Lastname,
     * theo thứ tự tăng dần.
     */
    @Override
    public int compareTo(Student another) {
        // TODO: Implement comparison logic
        // Compare by firstname first
        int firstNameComparison = this.firstname.compareTo(another.firstname);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }
        // If firstnames are equal, compare by lastname
        return this.lastname.compareTo(another.lastname);
    }

    // Optional: Override equals and hashCode for proper object comparison if needed
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return yearOfBirth == student.yearOfBirth &&
               Double.compare(student.mathsGrade, mathsGrade) == 0 &&
               Double.compare(student.physicsGrade, physicsGrade) == 0 &&
               Double.compare(student.chemistryGrade, chemistryGrade) == 0 &&
               Objects.equals(id, student.id) &&
               Objects.equals(lastname, student.lastname) &&
               Objects.equals(firstname, student.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, yearOfBirth, mathsGrade, physicsGrade, chemistryGrade);
    }
}
