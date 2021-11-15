package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaUserRepositoryTest extends UserServiceTest {

    @Autowired
    private UserService service;

    @Override
    public void get() {
        User user = service.get(USER_ID);
        List<Meal> result = user.getMeals();
        MEAL_MATCHER.assertMatch(result, meals);
    }
}