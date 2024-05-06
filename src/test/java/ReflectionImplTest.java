import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ReflectionImplTest {

    @Test
    void testCatCreationAndFieldValues() throws Exception {
        Constructor<Cat> constructor = Cat.class.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);


        Cat cat = constructor.newInstance(3);


        System.out.println("имя: " + cat.getName());
        System.out.println("возраст: " + cat.getAge());
        System.out.println("ноги: " + cat.getFeet());


        Assertions.assertEquals("Cat", cat.getName());
        Assertions.assertEquals(3, cat.getAge());
        Assertions.assertEquals(4, cat.getFeet());


        Field feetField = cat.getClass().getSuperclass().getDeclaredField("feet");
        feetField.setAccessible(true);
        feetField.set(cat, 6);

        Field ageField = cat.getClass().getSuperclass().getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(cat, 7);

        Field nameField = cat.getClass().getSuperclass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(cat, "Garfield");



        System.out.println("имя: " + cat.getName());
        System.out.println("возраст: " + cat.getAge());
        System.out.println("ноги: " + cat.getFeet());


        Assertions.assertEquals("Garfield", cat.getName());
        Assertions.assertEquals(7, cat.getAge());
        Assertions.assertEquals(6, cat.getFeet());
    }

    @Test
    void testPrivateConstructor() {
        assertThrows(Exception.class, () -> {
            Constructor<Cat> constructor = Cat.class.getDeclaredConstructor();
            constructor.setAccessible(false);
        });
    }

    @Test
    void testPrivateFields() {
        assertThrows(Exception.class, () -> {
            Cat cat = new Cat(2);
            Field feetField = cat.getClass().getSuperclass().getDeclaredField("feet");
            feetField.setAccessible(false);
            feetField.set(cat, 6);
        });
    }
}
