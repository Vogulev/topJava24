package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(START_SEQ + 2, USER_ID);
        assertMatch(meal, userMeals.get(0));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getSomeoneElseMeal() {
        assertThrows(NotFoundException.class, () -> service.get(START_SEQ + 2, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(START_SEQ + 2, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(START_SEQ + 2, USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void deleteSomeoneElseMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(START_SEQ + 2, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        assertMatch(service.getBetweenInclusive(LocalDate.of(2020, 1, 29),
                        LocalDate.of(2020, 1, 30), USER_ID),
                Arrays.asList(userMeals.get(0), userMeals.get(1), userMeals.get(2))
        );
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), userMeals);
    }

    @Test
    public void update() {
        service.update(updated, USER_ID);
        assertMatch(service.get(START_SEQ + 2, USER_ID), updated);
    }

    @Test
    public void duplicateDateTimeUpdate() {
        assertThrows(DataAccessException.class, () ->
                service.update(new Meal(START_SEQ + 3,
                        LocalDateTime.of(2020, 1, 30, 10, 0, 0),
                        "duplicate", 500), USER_ID));
    }

    @Test
    public void updateSomeoneElseMeal() {
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal newMeal = service.create(created, USER_ID);
        Integer newId = created.getId();
        Meal createdMeal = created;
        createdMeal.setId(newId);
        assertMatch(newMeal, createdMeal);
        assertMatch(service.get(newId, USER_ID), createdMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null,
                        LocalDateTime.of(2020, 1, 30, 10, 0, 0),
                        "duplicate", 500), USER_ID));
    }
}