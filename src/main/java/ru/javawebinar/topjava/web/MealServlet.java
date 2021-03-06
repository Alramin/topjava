package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.web.LoggerWrapper;
import ru.javawebinar.topjava.web.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("getAll");
        request.setAttribute("mealList",
                UserMealsUtil.getWithExceeded(UserMealsUtil.MEAL_LIST, 2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}
