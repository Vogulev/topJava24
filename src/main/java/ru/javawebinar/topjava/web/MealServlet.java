package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.InMemoryStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsData;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final Storage storage = new InMemoryStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            int mealId = Integer.parseInt(request.getParameter("id"));
            storage.delete(mealId);
        } else if (action != null && action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = storage.get(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
        }
        log.debug("creating list of mealsTo from list of meals");
        List<MealTo> mealsTos = MealsUtil.filteredByStreams(storage.getAll(),
                LocalTime.MIN, LocalTime.MAX, MealsData.CALORIES_PER_DAY);
        request.setAttribute("mealsTos", mealsTos);
        log.debug("redirect to meals");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String id = request.getParameter("id");
        Meal meal = new Meal(ldt, description, calories);
        if (id == null || id.isEmpty()) {
            storage.add(meal);
        } else {
            meal.setId(Integer.parseInt(id));
            storage.update(meal);
        }
        doGet(request, response);
    }
}
