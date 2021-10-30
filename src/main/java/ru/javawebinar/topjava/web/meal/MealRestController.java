package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public Meal get(int id) {
        return service.get(id, authUserId());
    }

    public void delete(int id) {
        service.delete(id, authUserId());
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void update(Meal meal) {
        service.update(meal, authUserId());
    }

    public Collection<MealTo> getAll() {
        return service.getAll(authUserId(), authUserCaloriesPerDay());
    }

    public Collection<MealTo> getAllFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return service.getAllFiltered(startDate, endDate, startTime, endTime, authUserId(), authUserCaloriesPerDay());
    }
}