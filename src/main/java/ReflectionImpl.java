import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class ReflectionImpl {
    public static void main(String[] args) throws Exception {
//        Здесь мы получаем конструктор класса cat.
        Constructor<Cat> constructor = Cat.class.getDeclaredConstructor(int.class);
//        если конструктор является приватным, мы делаем его public
        constructor.setAccessible(true);

//        здесь мы создаем объект cat без использования оператора new.
        Cat cat = constructor.newInstance(3);

//        печатаем стандартные значения полей объекта cat.
        System.out.println("Детали кошки:");
        System.out.println("имя: " + cat.getName());
        System.out.println("возраст: " + cat.getAge());
        System.out.println("ноги: " + cat.getFeet());

//        Следующие строки кода достигнут частных полей объекта cat и
//        установят новые значения в этих полях.
//        Здесь я намеренно не меняю количество ног.
        Field feetField = cat.getClass().getSuperclass().getDeclaredField("feet");
        feetField.setAccessible(true);
        feetField.set(cat, 4);

        Field ageField = cat.getClass().getSuperclass().getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(cat, 5);

        Field nameField = cat.getClass().getSuperclass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(cat, "Milo");

//        мы снова печатаем значения полей, чтобы проверить, произошли ли изменения.
        System.out.println("Детали кошки:");
        System.out.println("имя: " + cat.getName());
        System.out.println("возраст: " + cat.getAge());
        System.out.println("ноги: " + cat.getFeet());

    }
}
