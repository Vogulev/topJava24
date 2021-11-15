package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceTest;
import ru.javawebinar.topjava.service.UserService;

import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(DATAJPA)
public class DataJpaMealRepositoryTest extends MealServiceTest {

    @Autowired
    private MealService service;

    @Override
    public void get() {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        User user = meal.getUser();
        USER_MATCHER.assertMatch(user, UserTestData.user);
    }
}