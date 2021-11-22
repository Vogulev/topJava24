package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController {
    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String getMeals(Model model) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals",
                MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/delete")
    public String deleteMeals(HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        int id = getId(request);
        service.delete(id, userId);
        return "redirect:meals";
    }

    @GetMapping("/create")
    public String createMeal(HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/save")
    public String saveMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        int userId = SecurityUtil.authUserId();
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.hasLength(request.getParameter("id"))) {
            meal.setId(getId(request));
            service.update(meal, userId);
        } else {
            service.create(meal, userId);
        }
        return "redirect:meals";
    }

    @GetMapping("/update")
    public String updateMeal(HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        final Meal meal = service.get(getId(request), userId);
        request.setAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        List<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
        request.setAttribute("meals",
                MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime));
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
