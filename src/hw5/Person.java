package hw5;

class Person {
    private String fio, jobPosition, email, phoneNumber;
    private int salary, age;

    void about() {
        System.out.println("Person{" +
                "fio='" + fio + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}');
    }

    Person(String fio, String jobPosition, String email, String phoneNumber, int salary, int age) {
        this.fio = fio;
        this.jobPosition = jobPosition;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    int getAge() {
        return age;
    }
}
