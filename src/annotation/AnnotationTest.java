package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// ref: https://www.liaoxuefeng.com/wiki/1252599548343744/1265102026065728
public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("Bob", "Beijing", 20);
        Person p2 = new Person("", "Shanghai", 20);
        Person p3 = new Person("Alice", "Shanghai", 199);
        for (Person p : new Person[]{p1, p2, p3}) {
            try {
                check(p);
                System.out.println("Person " + p + " checked ok.");
            } catch (IllegalArgumentException e) {
                System.out.println("Person " + p + " checked failed: " + e);
            }
        }
    }

    static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        for (Field field : person.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                Object value = field.get(person);
                // TODO:
                switch (field.getName()) {
                    case "name" :
                        if (value instanceof String) {
                            String str = (String) value;
                            if (str.length() < range.min() || str.length() > range.max()) {
                                throw new IllegalArgumentException("Invalid length for " + field.getName());
                            }
                        }
                        break;
                    case "city":
                        if (value instanceof String) {
                            String str = (String) value;
                            if (str.length() > range.max()) {
                                throw new IllegalArgumentException("Invalid length for " + field.getName());
                            }
                        }
                        break;
                    case "age":
                        if (value instanceof Integer) {
                            int v = (Integer) value;
                            if (v < range.min() || v > range.max()) {
                                throw new IllegalArgumentException("Invalid length for " + field.getName());
                            }
                        }
                        break;
                }
            }
        }
    }

}

class Person {

    @Range(min = 1, max = 20)
    public String name;

    @Range(max = 10)
    public String city;

    @Range(min = 1, max = 100)
    public int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("{Person: name=%s, city=%s, age=%d}", name, city, age);
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default 0;
    int max() default 255;
}
