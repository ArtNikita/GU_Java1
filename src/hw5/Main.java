package hw5;

public class Main {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Person("Nikitin Nikita", "CEO", "nikitaartamonov@mail.ru", "89111111111", 300000, 20);
        persArray[2] = new Person("Artyomov Artyom", "Android developer", "artyomartyomov@mail.ru", "89212222222", 250000, 41);
        persArray[3] = new Person("Alekseev Alex", "IOS developer", "aleks@mail.ru", "89313333333", 250000, 25);
        persArray[4] = new Person("Aleksandrov Alexandr", "Manager", "alexxx@mail.ru", "89004444444", 90000, 50);
        for (Person person : persArray) {
            if(person.getAge() > 40){
                person.about();
            }
        }
    }
}
