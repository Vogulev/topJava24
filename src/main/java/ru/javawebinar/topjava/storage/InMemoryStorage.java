package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsData;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryStorage implements Storage {

    private static final List<Meal> meals = new CopyOnWriteArrayList<>();
    private final AtomicInteger count = new AtomicInteger(7);

    static {
        meals.addAll(MealsData.getList());
    }

    @Override
    public void add(Meal meal) {
        meal.setId(count.incrementAndGet());
        meals.add(meal);
    }

    @Override
    public Meal get(Integer id) {
        return meals.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        meals.remove(get(id));
    }

    @Override
    public void update(Meal meal) {
        Meal meal1 = meals.stream()
                .filter(m -> m.getId().equals(meal.getId()))
                .findFirst()
                .get();
        int index = meals.indexOf(meal1);
        meals.set(index, meal);
    }

    public List<Meal> getAll() {
        return meals;
    }

}
