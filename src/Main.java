import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
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
        // Поиск несовершеннолетних
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        // Поиск призывников
        persons.stream()
                .filter(w -> w.getAge() > 18 && w.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // Поиск людей с образованием
        persons.stream()
                .filter(w -> w.getAge() > 27)
                .sorted(Comparator.comparing(Person::getEducation))
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
