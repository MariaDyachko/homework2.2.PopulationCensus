import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        int kids = (int) persons.stream()   //Почему требует приведение?
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(kids);

        List<String> soldiers = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        for (String p: soldiers) {
            System.out.println(p);
        }

        List<Person> workGroup = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN ? x.getAge() >= 18 && x.getAge() <= 65 : x.getAge() >= 18 && x.getAge() <= 60)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        for (Person p: workGroup) {
            System.out.println(p);
        }

    }
}