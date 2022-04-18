package homework.census.netology;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static homework.census.netology.Sex.WOMAN;

public class Main {
    private static final List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    private static final List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    private static final Collection<Person> persons = new ArrayList<>();
    private static Boolean start = true;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
            data();
            while (start) {
                message();
            }
    }

    private static void data() {
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
    }

    private static void filter_18() {
        Stream<Person> stream = persons.stream();
        long result = stream
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("всего = " + result);
    }

    private static void filter18_27() {
        Stream<Person> stream = persons.stream();
        stream
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 27)
                .map(x -> String.valueOf(x.getFamily()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void filter() {
        Stream<Person> stream = persons.stream();
        stream
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() >= 18)
                .filter(x -> (x.getSex().equals(Sex.MAN) && x.getAge() < 65) || (x.getSex().equals(WOMAN) && x.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void message() {
        System.out.println("1. всего несовершеннолетних");
        System.out.println("2. список призывников");
        System.out.println("3. список потенциально работоспособных людей с высшим образованием");
        System.out.println("0. Выйти");
        input();
    }

    private static void input() {
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> filter_18();
            case 2 -> filter18_27();
            case 3 -> filter();
            case 0 -> exit();
            default -> System.out.println("повторите ввод");
        }
    }

    private static void exit() {
        start = false;
    }
}
