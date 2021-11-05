package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final List<Meal> userMeals = Arrays.asList(
            new Meal(START_SEQ + 2, LocalDateTime.of(2020, 1, 30, 10, 0, 0), "Завтрак", 500),
            new Meal(START_SEQ + 3, LocalDateTime.of(2020, 1, 30, 13, 0, 0), "Обед", 1000),
            new Meal(START_SEQ + 4, LocalDateTime.of(2020, 1, 30, 20, 0, 0), "Ужин", 500),
            new Meal(START_SEQ + 5, LocalDateTime.of(2020, 1, 31, 0, 0, 0), "Еда на граничное значение", 100),
            new Meal(START_SEQ + 6, LocalDateTime.of(2020, 1, 31, 10, 0, 0), "Завтрак", 1000),
            new Meal(START_SEQ + 7, LocalDateTime.of(2020, 1, 31, 13, 0, 0), "Обед", 500),
            new Meal(START_SEQ + 8, LocalDateTime.of(2020, 1, 31, 20, 0, 0), "Ужин", 410)
    );
    public static final List<Meal> adminMeals = Arrays.asList(
            new Meal(START_SEQ + 9, LocalDateTime.of(2020, 1, 31, 14, 0, 0), "Админ ланч", 510),
            new Meal(START_SEQ + 10, LocalDateTime.of(2020, 1, 31, 21, 0, 0), "Админ ужин", 1500)
    );

    public static final Meal updated = new Meal(START_SEQ + 2, LocalDateTime.of(2020, 1, 1, 1, 1, 1), "updated", 1);
    public static final Meal created = new Meal(null, LocalDateTime.of(2020, 10, 10, 10, 10, 10), "created", 10);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
