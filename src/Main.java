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
        System.out.println("Поиск несовершеннолетних");
        // Поиск несовершеннолетних
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        System.out.println("Поиск призывников");
        // Поиск призывников
        persons.stream()
                .filter(w -> w.getAge() > 18 && w.getAge() <= 27 && w.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Женщины с образованием от 18 до 60");
        // Поиск людей с образованием
        persons.stream()
                .filter(w -> w.getAge() > 18 && w.getAge() < 60 && w.getSex().equals(Sex.WOMAN))
                .sorted(Comparator.comparing(Person::getEducation))
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Мужчины с образованием от 18 до 65");
        // Поиск людей с образованием
        persons.stream()
                .filter(w -> w.getAge() > 18 && w.getAge() < 65 && w.getSex().equals(Sex.MAN))
                .sorted(Comparator.comparing(Person::getEducation))
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
