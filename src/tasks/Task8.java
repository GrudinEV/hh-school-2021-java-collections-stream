package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
    public List<String> getNames(List<Person> persons) {
        Map<Integer, String> personNames = getPersonNames(persons);
        return persons.stream()
                .skip(1)
                .map(person -> personNames.get(person.getId()))
                .collect(Collectors.toList());
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
        return new HashSet<>(getNames(persons));
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    public String convertPersonToString(Person person) {
        return person == null ? "" : ((person.getFirstName() == null ? "" : (person.getFirstName().trim() + " "))
                + (person.getSecondName() == null ? "" : person.getSecondName().trim()));
    }

    // словарь id персоны -> ее имя
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
        return new HashSet<>(persons).stream()
                .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
    }

    // есть ли совпадающие в двух коллекциях персоны?
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        List<Person> newPersons = new ArrayList<>(persons1);
        newPersons.addAll(persons2);
        return getDifferentNames(newPersons).size() != persons1.size() + persons2.size();
    }

    //...
    public long countEven(Stream<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0).count();
    }

    @Override
    public boolean check() {
        System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
        boolean codeSmellsGood = true;
        boolean reviewerDrunk = false;
        return codeSmellsGood || reviewerDrunk;
    }
}
